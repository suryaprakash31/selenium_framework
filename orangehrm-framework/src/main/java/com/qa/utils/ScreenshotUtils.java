package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String name) {             // CHANGED: returns a String
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Path.of("target", "screenshots", name + ".png");
            Files.createDirectories(destination.getParent());
            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + destination.toAbsolutePath());
            return destination.toAbsolutePath().toString();                           // CHANGED: hand back the path
        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
            return null;                                                              // CHANGED
        }
    }
}