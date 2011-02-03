package info.guardianproject.luks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import android.util.Log;
import android.widget.Toast;

public class LUKSManager {

	private final static String LOSETUP_BIN = "losetup";
	private final static String CRYPTSETUP_BIN = "/data/local/cryptsetup";
	
	private final static String TAG = "luks";
	
	public static String getLoopbackPath () throws Exception
	{
		//system/bin/losetup -f
		
		String[] cmds = {LOSETUP_BIN + " -f"};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;		Log.i(TAG, log.toString());

		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		return log.toString();
	}
	
	public static int createMountPath (String mountPath) throws Exception
	{
		//mkdir /mnt/sdcard/foo
		
		String[] cmds = {"mkdir " + mountPath};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		return err;
	}
	
	public static int createStoreFile (String loopback, String storePath, int size, String password) throws Exception
	{
		
		//mknod /dev/loop0 b 7 0
		//losetup /dev/loop0 /mnt/sdcard/secretagentman.mp3
		//dd if=/dev/zero of=/mnt/sdcard/secretagentman.mp3 bs=1M count=50000000
		
//		cryptsetup luksFormat -c aes-plain /dev/loop0

		
		String[] cmds = {
				"mknod " + loopback + " b 7 0",
				"dd if=/dev/zero of=" + storePath + " bs=1M count=" + size,
				LOSETUP_BIN + " " + loopback + " " + storePath,
				CRYPTSETUP_BIN + " luksFormat -c aes-plain " + loopback
		};
		
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		
		//logNotice("executing shell cmds: " + cmds[0] + "; runAsRoot=" + runAsRoot);
		
		Process proc = null;
		int exitCode = -1;
		
            
    	if (runAsRoot)
    		proc = Runtime.getRuntime().exec("su");
    	else
    		proc = Runtime.getRuntime().exec("sh");
    	
    	
    	PrintStream stdin = new PrintStream(proc.getOutputStream());
		BufferedReader stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
	
		// Consume the "stdout"
		String line = null;
	
		for (int i = 0; i < cmds.length; i++)
		{
			stdin.println(cmds[i]);
		
			//proc.waitFor();
			line = stdout.readLine();
			Log.d("luks",line);
			
			while (stdout.ready())
			{
				line = stdout.readLine();
				Log.d("luks",line);
				
			}
			
		}
		//confirm YES
		stdin.println("YES");
		
		if (stdout.ready())
		{
			//send password
			line = stdout.readLine();
			stdin.println(password);
			
			//confirm pwd
			line = stdout.readLine();
			stdin.println(password);
	
		}        
        
		stdin.flush();
		stdin.println("exit");
		stdin.flush();
		
			
        
	
		return exitCode;
		

	}
	
	public static int formatMountPath (String devmapper) throws Exception
	{
		////mke2fs -O uninit_bg,resize_inode,extent,dir_index -L DroidCrypt0 -FF /dev/mapper/crypttest
		String[] cmds = {
				"mke2fs -O uninit_bg,resize_inode,extent,dir_index -L " + devmapper + " -FF /dev/mapper/" + devmapper
				
		};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		Log.i(TAG, log.toString());

		return err;
	}
	
	public static int open (String loopback, String devmapper, String password) throws Exception
	{
//		cryptsetup luksOpen /dev/loop0 secretagentman
		String cmd = CRYPTSETUP_BIN + " luksOpen " + loopback + " " + devmapper;
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		
		//logNotice("executing shell cmds: " + cmds[0] + "; runAsRoot=" + runAsRoot);
		
		Process proc = null;
		int exitCode = -1;
		
            
    	if (runAsRoot)
    		proc = Runtime.getRuntime().exec("su");
    	else
    		proc = Runtime.getRuntime().exec("sh");
    	
    	
    	PrintStream stdin = new PrintStream(proc.getOutputStream());
		BufferedReader stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		
		stdin.println(cmd);
        
		// Consume the "stdout"
		String line = stdout.readLine();
		
		stdin.println(password);
        
        
		stdin.flush();
		stdin.println("exit");
		stdin.flush();
		
		Log.i(TAG, log.toString());

        
		
		return exitCode;
	
	}

	public static int mount (String devmapper, String mountPath) throws Exception
	{
		//	//mount /dev/mapper/secretagentman /mnt/sdcard/secretagentman
		String[] cmds = {
				"mkdir " + mountPath,
				"mount /dev/mapper/" + devmapper + " " + mountPath
		};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		Log.i(TAG, log.toString());
		
		return err;
	}
	
	public static String getStatus (String devmapper) throws Exception
	{
		//cryptsetup status secretagentman

		String[] cmds = {
				CRYPTSETUP_BIN + " status " + devmapper
		};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		return log.toString();
		
	}
	public static int close (String devmapper, String mountPath) throws Exception
	{
		
		String[] cmds = {
				"umount  " + mountPath,
				CRYPTSETUP_BIN + " luksClose " + devmapper
		};
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		return err;
	}
	
	public static int delete (String storePath, String mountPath, String devmapper, String loopback) throws Exception
	{
		close (devmapper, mountPath);
		
		String[] cmds = {
				"rm -rf  " + mountPath,
				"rm -rf  " + storePath,
				"rm -rf  " + loopback
		};
		
		StringBuilder log = new StringBuilder ();
		boolean runAsRoot = true;
		boolean waitFor = true;
		
		int err = ServiceShellUtils.doShellCommand(cmds, log, runAsRoot, waitFor);
		
		return err;
		
	}
}
