package com.ntorressm.pcst;

import java.io.File;

public class Compiler {

    File javaFile;
    File classFile;

    public Compiler(File javaFile) {
        this.javaFile = javaFile;
    }

    // TODO: REMOVE folder with all files
    public void delete() {

    }

    public File compile() throws Exception {
        String command = String.format("javac %s -d .", javaFile);
        Process p = Runtime.getRuntime().exec(command);

        p.waitFor();
        p.destroy();

        classFile = new File(javaFile.getName().substring(0, javaFile.getName().length()-4)+"class");

        return classFile;
    }
}
