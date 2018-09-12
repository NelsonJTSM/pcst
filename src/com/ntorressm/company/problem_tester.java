package com.ntorressm.company;

import java.io.*;

public class problem_tester {

    static String filename;

    /*
    Current correct and only way to call this class
    java problem_tester dir/to/testing/file/filename.java dir/to/testing/input.in dir/to/testing/output.out
     */

    public static void main(String[] args) throws Exception {
        if (args.length == 3) {
            //System.out.printf("Successful arguments: %s %s %s", args[0], args[1], args[2]);

            filename = args[0].substring(0, args[0].length()-5);
            System.out.printf("filename: %s\n", filename);

            // RUN Solution File
            runProcess("javac " + args[0]);
            System.out.println("Successfully compiled");

            // Pipe Input into Solution
            String command2 = "cat " + args[1] + " | java " + filename;
            System.out.println("Trying command: " + command2);
            OutputStream is = runProcess(command2);

            // Save output
            System.out.println("InputStream is\n" + getOutput(is));

            // Compare solution output to correct output

            // Print out true or false whether it is right or wrong
        } else {
            System.out.println("ERROR: Incorrect format");
            System.out.println("Use this format: java problem_tester dir/to/testing/file/filename.java dir/to/testing/input.in dir/to/testing/output.out");
            System.out.println("Current directory: " + System.getProperty("user.dir"));
        }
    }

    private static OutputStream runProcess(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        // Handle error
        return process.getOutputStream();
    }

    private static String getOutput(OutputStream is) throws IOException {
        return null;
    }
}
