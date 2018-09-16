package com.ntorressm.pcst;

import org.apache.commons.cli.Options;

public class Main {

    private static String javaFilename;
    private static String javaDirectory;

    private static Compiler compiler;
    private static ProgramRunner pr;

    // Example command line input:
    // java Main test /dir/to/test
    // Assumes input and output have the same name as class,
    // located in the same directory, and have extensions .in and .out
    // (UCF Old Standard)
    public static void main(String[] args) throws Exception {
        // Parse args

        // Initialize variables
        javaFilename = args[0];
        javaDirectory = args[1];
        compiler = new Compiler(javaFilename, javaDirectory);

        // Compile .java file
        compiler.compile();

        // Test .class file
        pr = new ProgramRunner(javaFilename);
        pr.setDirectory(args[1]);
        pr.start();

        // Delete .class file
        compiler.delete();
    }
}
