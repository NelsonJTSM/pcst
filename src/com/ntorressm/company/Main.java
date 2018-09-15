package com.ntorressm.company;

public class Main {

    // Example command line input:
    // java Main test /dir/to/test
    // Assumes input and output have the same name as class,
    // located in the same directory, and have extensions .in and .out
    // (UCF Old Standard)
    public static void main(String[] args) throws Exception {
        // Initialize variables
        String filename = args[0];
        String directory = args[1];

        // Compile .java file
        Compiler compiler = new Compiler(filename, directory);
        compiler.compile();

        // Test .class file
        ProgramRunner pr = new ProgramRunner(filename);
        pr.setDirectory(args[1]);
        pr.start();

        // Delete .java file
        compiler.delete();
    }
}
