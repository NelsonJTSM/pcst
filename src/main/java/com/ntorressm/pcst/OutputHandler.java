package com.ntorressm.pcst;

import java.io.*;

public class OutputHandler {

    private File file;
    private InputStream stdout;

    private BufferedReader javaOutputReader;
    private BufferedReader outputFileReader;

    private int incorrectLineNumber;
    private String javaOutputLine;
    private String correctLine;
    private Result result;

    public OutputHandler(File file) {
        this.file = file;
    }

    public void update() throws Exception {
        javaOutputReader = new BufferedReader(new InputStreamReader(stdout));
        outputFileReader = new BufferedReader(new FileReader(file));
        result = Result.CORRECT; // Assume it's correct until proven otherwise

        int lineNumber = 0;
        while ((correctLine = outputFileReader.readLine()) != null) {
            lineNumber++;
            javaOutputLine = javaOutputReader.readLine();

            if (javaOutputLine == null) {
                result = Result.SHORTINPUT;
                javaOutputLine = "NO OUTPUT";
                incorrectLineNumber = lineNumber;
                break;
            }

            if (!correctLine.equals(javaOutputLine)) {
                result = Result.INCORRECT;
                incorrectLineNumber = lineNumber;
                break;
            }
        }

        // TODO: Check for long input

        // Close readers
        outputFileReader.close();
        javaOutputReader.close();
    }

    public void setStdout(InputStream stdout) {
        this.stdout = stdout;
    }

    public int getIncorrectLineNumber() {
        return  incorrectLineNumber;
    }

    public String getJavaOutputLine() {
        return javaOutputLine;
    }

    public String getCorrectLine() {
        return correctLine;
    }

    public Result getResult() {
        return result;
    }
}
