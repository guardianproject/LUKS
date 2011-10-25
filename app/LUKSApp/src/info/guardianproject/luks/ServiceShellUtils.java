/* Copyright (c) 2009, Nathan Freitas, Orbot / The Guardian Project - http://openideals.com/guardian */
/* See LICENSE for licensing information */
package info.guardianproject.luks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import android.util.Log;

public class ServiceShellUtils {

	private final static String TAG = "LUKS";
	private final static boolean LOG_OUTPUT_TO_DEBUG = true;

	// various console cmds
	public final static String SHELL_CMD_CHMOD = "chmod";
	public final static String SHELL_CMD_KILL = "kill -9";
	public final static String SHELL_CMD_RM = "rm";
	public final static String SHELL_CMD_PS = "ps";
	public final static String SHELL_CMD_PIDOF = "pidof";

	public final static String CHMOD_EXE_VALUE = "777";

	/**
	 * Check if we have root access
	 * 
	 * @return boolean true if we have root
	 */
	public static boolean checkRootAccess() {

		StringBuilder log = new StringBuilder();

		try {

			// Run an empty script just to check root access
			String[] cmd = { "exit 0" };
			int exitCode = ServiceShellUtils.doShellCommand(cmd, log, true,
					true);
			if (exitCode == 0) {

				return true;
			}

		} catch (IOException e) {
			// this means that there is no root to be had (normally) so we won't
			// log anything
		} catch (Exception e) {
			Log.w(TAG, "Error checking for root access: " + e.getMessage());
			// this means that there is no root to be had (normally)
		}

		logNotice("Could not acquire root permissions");
		return false;
	}

	private static void logNotice(String msg) {
		if (LOG_OUTPUT_TO_DEBUG)
			Log.d(TAG, msg);
	}

	public static int findProcessId(String command) {
		int procId = -1;

		try {
			procId = findProcessIdWithPidOf(command);

			if (procId == -1)
				procId = findProcessIdWithPS(command);
		} catch (Exception e) {
			try {
				procId = findProcessIdWithPS(command);
			} catch (Exception e2) {
				Log.w(TAG, "Unable to get proc id for: " + command, e2);
			}
		}

		return procId;
	}

	// use 'pidof' command
	public static int findProcessIdWithPidOf(String command) throws Exception {

		int procId = -1;

		Runtime r = Runtime.getRuntime();

		Process procPs = null;

		String baseName = new File(command).getName();
		// fix contributed my mikos on 2010.12.10
		procPs = r.exec(new String[] { SHELL_CMD_PIDOF, baseName });
		// procPs = r.exec(SHELL_CMD_PIDOF);

		BufferedReader reader = new BufferedReader(new InputStreamReader(procPs
				.getInputStream()));
		String line = null;

		while ((line = reader.readLine()) != null) {

			try {
				// this line should just be the process id
				procId = Integer.parseInt(line.trim());
				break;
			} catch (NumberFormatException e) {
				logNotice("unable to parse process pid: " + line);
			}
		}

		return procId;

	}

	// use 'ps' command
	public static int findProcessIdWithPS(String command) throws Exception {

		int procId = -1;

		Runtime r = Runtime.getRuntime();

		Process procPs = null;

		procPs = r.exec(SHELL_CMD_PS);

		BufferedReader reader = new BufferedReader(new InputStreamReader(procPs
				.getInputStream()));
		String line = null;

		while ((line = reader.readLine()) != null) {
			if (line.indexOf(' ' + command) != -1) {

				StringTokenizer st = new StringTokenizer(line, " ");
				st.nextToken(); // proc owner

				procId = Integer.parseInt(st.nextToken().trim());

				break;
			}
		}

		return procId;

	}

	public static int doShellCommand(String[] cmds, StringBuilder log,
			boolean runAsRoot, boolean waitFor) throws Exception {

		logNotice("Executing shell cmds in process: runProcessAsRoot="
				+ runAsRoot + "; waitOnProcess=" + waitFor);
		for (String cmd : cmds)
			logNotice("\t" + cmd.replace('\n', ' '));

		Process proc = null;

		if (runAsRoot)
			proc = Runtime.getRuntime().exec("su");
		else
			proc = Runtime.getRuntime().exec("sh");

		// Execute all commands and exit the process
		OutputStreamWriter out = new OutputStreamWriter(proc.getOutputStream());
		for (String cmd : cmds)
			out.write(cmd + "\n");
		out.write("exit\n");
		out.flush();

		// Are we waiting for the output?
		if (waitFor == false)
			return -1;

		// Are we returning the logs?
		if (log != null) {
			final char buf[] = new char[1024];

			// Consume the "stdout"
			InputStreamReader reader = new InputStreamReader(proc
					.getInputStream());
			int read = 0;
			while ((read = reader.read(buf, 0, 1024)) != -1) {
				log.append(buf, 0, read);
			}

			// Consume the "stderr"
			reader = new InputStreamReader(proc.getErrorStream());
			while ((read = reader.read(buf, 0, 1024)) != -1) {
				log.append(buf, 0, read);
			}
		}

		int exitCode = proc.waitFor();
		log.append("Process exit code: ");
		log.append(exitCode);
		log.append("\n");

		logNotice("command process exit value: " + exitCode);

		return exitCode;

	}
}
