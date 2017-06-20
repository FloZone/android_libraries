package fr.frodriguez.library;

import java.io.DataOutputStream;
import java.io.IOException;

import fr.frodriguez.library.utils.StringUtils;

/**
 * By FloZone on 11/02/2017.
 */

public class ShellCommand {
    private String[] commands;
    private String output, error, localError;
    private int exitValue;
    private boolean asRoot;


    public ShellCommand(String[] commands){
        this.commands = commands;
    }

    public String[] getCommands() {
        return commands;
    }
    public String getOutput() {
        return output;
    }
    public String getError() {
        return error;
    }
    public String getLocalError() {
        return localError;
    }
    public int getExitValue() {
        return exitValue;
    }

    /**
     * Return true wether an error occurs
     */
    public boolean isError() {
        return exitValue != 0
                && error == null
                && localError == null;
    }

    /**
     * Execute a shell command
     * @param commands the commands to run
     * @return a ShellCommand object containing execution outputs
     */
    public static ShellCommand run(String[] commands) {
        ShellCommand shellCommand = new ShellCommand(commands);
        shellCommand.asRoot = false;

        try {
            // Execute the command
            Process process = Runtime.getRuntime().exec(commands);
            process.waitFor();

            // Get the execution outputs
            shellCommand.output    = StringUtils.getStringValue(process.getInputStream()).trim();
            shellCommand.error     = StringUtils.getStringValue(process.getErrorStream()).trim();
            shellCommand.exitValue = process.exitValue();

            // Destroy the shell process
            process.destroy();
        } catch (Exception e) {
            shellCommand.localError = e.getMessage();
        }
        return shellCommand;
    }

    /**
     * Execute a shell command as root
     * @param commands the commands to run
     * @return a ShellCommand object containing execution outputs
     */
    public static ShellCommand runAsRoot(String[] commands) {
        ShellCommand shellCommand = new ShellCommand(commands);
        shellCommand.asRoot = true;

        try {
            // Get a root shell
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());

            // Write the commands
            for (String cmd : commands) {
                os.writeBytes(cmd+"\n");
            }
            os.writeBytes("exit\n");

            // Execute them
            os.flush();
            os.close();
            process.waitFor();

            // Get the execution outputs
            shellCommand.output    = StringUtils.getStringValue(process.getInputStream()).trim();
            shellCommand.error     = StringUtils.getStringValue(process.getErrorStream()).trim();
            shellCommand.exitValue = process.exitValue();

            // Destroy the shell process
            process.destroy();
        } catch (Exception e) {
            shellCommand.localError = e.getMessage();
        }
        return shellCommand;
    }

}
