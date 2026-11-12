package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report/report.html");
            spark.config().setReportName("OrangeHRM Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}