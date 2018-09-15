package com.ntorressm.company;

import java.io.File;

public class Compiler {

    String filename;
    String directory;
    File classFile;

    public Compiler(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;
        this.classFile = new File(filename + ".class");
    }

    public void delete() {
        classFile.delete();
    }

    public void compile() throws Exception {
        String command = String.format("javac %s/%s.java -d .", directory, filename);
        Process p = Runtime.getRuntime().exec(command);

        p.waitFor();
        p.destroy();

        System.out.printf("Compiled %s.java\n", filename);
    }
}
