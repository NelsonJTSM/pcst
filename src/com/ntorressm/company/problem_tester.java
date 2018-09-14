package com.ntorressm.company;

import java.io.*;

/*
TODO: Correctly handle execeptions
TODO: Handle input from file
TODO: Handle correct output from file
TODO: Add arguments handling (-d for changing directory e.g. instead of having to pass it)
 */

public class problem_tester {

    static String filename;

    // Solely used for testing
    static String staticTestInput = "6\nHello\nWorld!\nI am\ncorrect\nNo\nyou're not";
    static String staticTestOutput = "Hello World!I am correctNo you're not";

    static File processingDirectory;
    static String processingOutput;

    static File correctInputFile;
    static File correctOutputFile;


    /*
    Current correct and only way to call this class
    java problem_tester test dir/to/testing/ dir/to/testing/input.in dir/to/testing/output.out
     */

    public static void main(String[] args) throws Exception {
        if (args.length == 4) {
            System.out.printf("Successful arguments: %s %s %s %s\n", args[0], args[1], args[2], args[3]);

            // Process args //
            filename = args[0];
            processingDirectory = new File(args[1]);
            correctInputFile = new File(args[2]);
            correctOutputFile = new File(args[3]);

            // RUN Solution File
            runSolution();

            // Compare solution output to correct output
            if (processingOutput.equals(staticTestOutput)) {
                System.out.println("Correct");
            } else {
                System.out.println("Incorrect");
            }
        } else {
            System.out.println("ERROR: Incorrect format");
            System.out.println("Use this format: java problem_tester dir/to/testing/file/filename.java dir/to/testing/input.in dir/to/testing/output.out");
            System.out.println("Current directory: " + System.getProperty("user.dir"));
        }
    }

    public static void runSolution() throws Exception {
        System.out.println("Running solution");

        ProcessBuilder processingBuilder = new ProcessBuilder("java", filename);
        processingBuilder.directory(processingDirectory);
        processingBuilder.redirectErrorStream(true);
        Process process = processingBuilder.start();

        OutputStream stdin = process.getOutputStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        System.out.println("pid: " + process.pid());;


        // Test input into the process
        writeInput(writer);

        // Read output
        readOutput(reader);

        // Finished with BufferedReader/Writer
        reader.close();
    }

    /*
    For now saves the output of the processed program into a String
    TODO: Save output in a different way (to more easily compare
     */
    public static void readOutput(BufferedReader reader) throws Exception {
        System.out.println("Reading output...");

        try {
            processingOutput = "";
            String lastLine; // Assumes there is a first line to begin with

            while ((lastLine = reader.readLine()) != null) {
                processingOutput += lastLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finished reading output!");
    }

    /*
    TODO: Write this function correctly, for now it passes a static String in this class
     */
    public static void writeInput(BufferedWriter writer) throws Exception {
        System.out.println("Writing output...");

        writer.write(staticTestInput);

        writer.flush();
        writer.close();

        System.out.println("Finished writing output!");
    }
}
