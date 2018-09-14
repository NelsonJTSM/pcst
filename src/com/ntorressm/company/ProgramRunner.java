package com.ntorressm.company;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class ProgramRunner {

    private InputHandler inputHandler;
    private OutputHandler outputHandler;

    private String javaFileName;
    private File javaFileDirectory;

    private ProcessBuilder processBuilder;
    private Process process;

    public ProgramRunner(String[] args) throws Exception {
        // Initialize all necessary variables
        inputHandler = new InputHandler();
        outputHandler = new OutputHandler();

        parseArgs(args);

        startProcess();

        handleInput();

        handleOutput();

        endProcess();

        checkSolution();
    }

    // TODO: Correctly parse arguments
    /* Current only works for Main classname dir/to/class dir/to/input/filename dir/to/output/filename */
    private void parseArgs(String[] args) {
        javaFileName = args[0];
        javaFileDirectory = new File(args[1]);
        inputHandler.setFile(new File(args[2]));
        outputHandler.setFile(new File(args[3]));
    }

    // TODO: Compile the .java file
    private void startProcess() throws Exception {
        processBuilder = new ProcessBuilder("java", javaFileName);
        processBuilder.directory(javaFileDirectory);
        processBuilder.redirectErrorStream(true);
        process = processBuilder.start();

        inputHandler.setStdin(process.getOutputStream());
        outputHandler.setStdout(process.getInputStream());
    }

    private void handleInput() throws Exception {
        inputHandler.update();
    }

    private void handleOutput() throws Exception {
        outputHandler.update();
    }

    private void endProcess() {
        process.destroy();
    }

    private void checkSolution() {
        switch (outputHandler.getResult()) {
            case CORRECT:
                System.out.println("The output is correct");
                break;
            case INCORRECT:
                System.out.println("The output is incorrect on line " + outputHandler.getIncorrectLineNumber());
                System.out.println("Correct output: " + outputHandler.getCorrectLine());
                System.out.println("Program's output: " + outputHandler.getJavaOutputLine());
                break;
            case LONGINPUT:
                System.out.println("The program has too much output");
                break;
            case SHORTINPUT:
                System.out.println("The program has too little output");
                break;
                default:
                    System.out.println("I don't know what you did, but you must have really messed up");
        }
    }
}
