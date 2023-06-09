package pl.edu.pw.ee;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileIO {
    private final static Charset charset = StandardCharsets.UTF_8;

    public static FileReader getNewFileReader(String name) throws IOException {
        return new FileReader(name, charset);
    }

    public static FileReader getNewFileReader(File name) throws IOException {
        return new FileReader(name, charset);
    }

    public static FileWriter getNewFileWriter(String name) throws IOException {
        return new FileWriter(name, charset);
    }

    public static FileWriter getNewFileWriter(File name) throws IOException {
        return new FileWriter(name, charset);
    }
}
