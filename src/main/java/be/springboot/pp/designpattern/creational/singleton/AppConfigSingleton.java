package be.springboot.pp.designpattern.creational.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfigSingleton {
    private final Properties properties;

    private AppConfigSingleton() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("./src/main/resources/application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Config load failed");
        }
    }

    private static final class InstanceHolder {
        private static final AppConfigSingleton instance = new AppConfigSingleton();
    }

    public static AppConfigSingleton getInstance() {
        return InstanceHolder.instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
