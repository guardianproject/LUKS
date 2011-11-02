package info.guardianproject.luks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import android.util.Log;

public class LUKSManager {

	private final static String LOSETUP_BIN = "losetup";
	private static String CRYPTSETUP_BIN = "cryptsetup";

	private final static String TAG = "LUKS";

	/* Does this work on Android / Busybox's losetup? Seems not to...
	 * public static String getLoopbackPath() throws Exception {
		// system/bin/losetup -f

		String[] cmds = { LOSETUP_BIN + " -f" };
		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		Log.i(TAG, log.toString());

		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);

		return log.toString();
	}*/

	public static void setCryptSetupPath (String path)
	{
		CRYPTSETUP_BIN = path;
	}
	
	public static int createMountPath(String mountPath) throws Exception {
		// mkdir /mnt/sdcard/foo

		String[] cmds = { "mkdir " + mountPath };
		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);
		
		printLog(log);

		return err;
	}

	public static int createStoreFile(String loopback, String storePath,
			int size, String password) throws Exception {

		// mknod /dev/loop0 b 7 0
		// losetup /dev/loop0 /mnt/sdcard/secretagentman.mp3
		// dd if=/dev/zero of=/mnt/sdcard/secretagentman.mp3 bs=1M
		// count=50000000
		// echo "Pass" | cryptsetup -q luksFormat --key-file=- -c aes-plain
		// /dev/loop0

		String[] cmds = {
				"mknod " + loopback + " b 7 0",
				"dd if=/dev/zero of=" + storePath + " bs=1M count=" + size,
				LOSETUP_BIN + " " + loopback + " " + storePath,
				"echo \"" + password + "\" | " + CRYPTSETUP_BIN
						+ " -q --key-file=- luksFormat -c aes-plain "
						+ loopback };

		StringBuilder log = new StringBuilder();
		int exitCode = ServiceShellUtils.doShellCommand(cmds, log, true, true);

		printLog(log);

		return exitCode;
	}

	/**
	 * When the LUKS-enabled drive is first exposed through {@code
	 * /dev/mapper/<devmapper>}, it appears as a block device with no
	 * filesystem. This method creates a filesystem that will live within the
	 * LUKS-enabled drive. The created filesystem can then be mounted like any
	 * other partition with a filesystem. Details of the created filesystem
	 * follow:
	 * 
	 * <br />
	 * <br />
	 * The <tt>mke2fs</tt> command is used to create a filesystem. An example of
	 * the full command is: <br /> {@code mke2fs -O
	 * uninit_bg,resize_inode,extent,dir_index -L luksdm -FF /dev/mapper/luksdm} <br />
	 * <br />
	 * The following arguments are passed to <tt>mke2fs</tt>:
	 * <dl>
	 * <dt>-O</dt>
	 * <dd>Says we are providing custom filesystem options</dd>
	 * <dt>uninit_bg</dt>
	 * <dd>Creates a filesystem without initializing all the block groups.
	 * Speeds up filesystem creation. Only supported by ext4. Note: Are we
	 * creating an ext4 FS? Is this even useful?</dd>
	 * <dt>resize_inode</dt>
	 * <dd>Leaves space so block table can grow in future, allowing this
	 * filesystem to be increased in size at a later point</dd>
	 * <dt>extent</dt>
	 * <dd>more efficient/faster scheme than indirect blocks for accessing
	 * filesystem</dd>
	 * <dt>dir_index</dt>
	 * <dd>Use hashed b-trees to speedup lookups in large directories</dd>
	 * <dt>-L luksdm</dt>
	 * <dd>Set volume label to luksdm</dd>
	 * </dl>
	 * 
	 * Some other options that I removed:
	 * <dl>
	 * <dt>-FF</dt>
	 * <dd>Two times the '-F' flag. Force filesystem creation, even if the
	 * current filesystem appears to be in use or is already mounted.</dd>
	 * </dl>
	 * 
	 * 
	 * @param devmapper
	 * @return
	 * @throws Exception
	 */
	public static int formatMountPath(String devmapper) throws Exception {
		String[] cmds = { "mke2fs -O uninit_bg,resize_inode,extent,dir_index -L "
				+ devmapper + " /dev/mapper/" + devmapper };

		StringBuilder log = new StringBuilder();
		int err = ServiceShellUtils.doShellCommand(cmds, log, true, true);

		printLog(log);

		return err;
	}

	public static int open(String loopback, String devmapper, String password)
			throws Exception {
		// echo "pass" | cryptsetup luksOpen --key-file=- /dev/loop0 luksdm
		String[] cmds = { "echo \"" + password + "\" | " + CRYPTSETUP_BIN
				+ " luksOpen --key-file=- " + loopback + " " + devmapper };
		StringBuilder log = new StringBuilder();

		int exitCode = ServiceShellUtils.doShellCommand(cmds, log, true, true);

		printLog(log);

		return exitCode;
	}

	public static int mount(String devmapper, String mountPath)
			throws Exception {
		// Mount -o rw -t ext3 /dev/mapper/luksdm /mnt/sdcard/secret
		String[] cmds = {
				"mkdir " + mountPath,
				"mount -o rw -t ext3 /dev/mapper/" + devmapper + " "
						+ mountPath };
		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);

		printLog(log);

		return err;
	}

	public static String getStatus(String devmapper) throws Exception {
		// cryptsetup status secretagentman

		String[] cmds = { CRYPTSETUP_BIN + " status " + devmapper };
		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);
		printLog(log);

		return log.toString();

	}

	public static int close(String devmapper, String mountPath)
			throws Exception {

		String[] cmds = { "umount  " + mountPath,
				CRYPTSETUP_BIN + " luksClose " + devmapper };
		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);
		
		printLog(log);

		return err;
	}

	public static int delete(String storePath, String mountPath,
			String devmapper, String loopback) throws Exception {
		close(devmapper, mountPath);

		String[] cmds = { "rm -r " + mountPath, "rm " + storePath,
				"rm " + loopback };

		StringBuilder log = new StringBuilder();
		boolean runAsRoot = true;
		boolean waitFor = true;

		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot,
				waitFor);
		
		printLog(log);

		return err;
	}
	
	private static void printLog(StringBuilder log) {
		String[] lines = log.toString().split("^");
		if (lines.length != 0)
			Log.v(TAG, "Process stdout + stderr:");
		for (String line : lines)
			if (line.length() != 0)
				Log.v(TAG, "\t" + line);
	}
}
