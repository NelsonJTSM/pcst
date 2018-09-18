package com.ntorressm.pcst;

import org.apache.commons.cli.*;

import java.io.File;

public class Main {

    private static String javaFilename;
    private static String javaDirectory;
    private static String inputFilename;
    private static String inputDirectory;
    private static String outputFilename;
    private static String outputDirectory;

    private static File classFile;
    private static File javaFile;
    private static File inputFile;
    private static File outputFile;

    private static Compiler compiler;
    private static ProgramRunner pr;

    private static Options options;
    private static CommandLineParser parser;
    private static CommandLine line;

    // TODO: Use apache files

    // Example command line input:
    // java Main test.java /dir/to/test
    // Assumes input and output have the same name as class,
    // located in the same directory, and have extensions .in and .out
    // (UCF Old Standard)
    public static void main(String[] args) throws Exception {
        // Parse args
        parseArgs(args);

        // Compile .java file
        compiler = new Compiler(javaFile);
        classFile = compiler.compile();

        // Test .class file
        pr = new ProgramRunner(classFile, inputFile, outputFile);
        pr.start();

        // Delete .class file
        compiler.delete();
    }

    private static void parseArgs(String[] args) throws Exception {
        // Crappy argument parsing
        javaFilename = args[0];
        javaDirectory = args[1];
        javaFile = new File(javaDirectory + "/" + javaFilename);
        inputFile = new File(javaFile.getPath().substring(0, javaFile.getPath().length()-4) + "in");
        outputFile = new File(javaFile.getPath().substring(0, javaFile.getPath().length()-4) + "out");

        /*

        parser = new DefaultParser();
        String lineValue;

        try {
            line = parser.parse(options, args);

            if (line.hasOption("dir")) {
                lineValue = line.getOptionValue("dir");
                javaDirectory = lineValue;
            } else {
                javaDirectory =  ".";
            }

            if (line.hasOption("dirfile")) {

            } else {

            }
        } catch (ParseException pe) {
            System.err.println("You messed up: " + pe.getMessage());
        }
        */
    }

    private static void createOptions() {
        options = new Options();

        options.addOption("dir", true,"change directory of java, input, and output files");
        options.addOption("dirfile", true, "change directory of input/output files");
        options.addOption("dirin", true, "change directory of input file");
        options.addOption("dirout", true, "change directory of output file");

        options.addOption("input", true, "set input file");
        options.addOption("output", true, "set output file");
        options.addOption("inputname", true, "set input filename (with extension)");
        options.addOption("outputname", true, "set output filename (with extension)");
    }
}
