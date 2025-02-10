package be.springboot.pp.logframework.subscribers;

import be.springboot.pp.logframework.enums.LogLevel;

import java.io.FileWriter;
import java.io.IOException;

public class FileSubscriber implements Subscriber {
    private final String name;

    public FileSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(LogLevel newsType, String data) {
        System.out.println(name + " received " + newsType + " data: " + data);
        try (FileWriter writer = new FileWriter(name, true)) {
            writer.write(data + "\n");
            System.out.println("📁 Writing Data to File: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
