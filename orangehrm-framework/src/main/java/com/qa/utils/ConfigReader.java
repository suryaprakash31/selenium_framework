package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    // Runs once: load the shared file, then the local one (which overrides it)
    static {
        loadFile("src/test/resources/config.properties", true);
        loadFile("src/test/resources/config.local.properties", false);
    }

    private static void loadFile(String path, boolean required) {
        try (FileInputStream file = new FileInputStream(path)) {
            properties.load(file);
        } catch (IOException e) {
            if (required) {
                throw new RuntimeException("Could not read " + path, e);
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}