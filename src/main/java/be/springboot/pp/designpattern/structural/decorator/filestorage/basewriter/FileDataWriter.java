package be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileDataWriter implements DataWriter {
    private final String filename;

    public FileDataWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");
            System.out.println("📁 Writing Data to File: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
