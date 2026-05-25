package com.onesports.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsTestNG {

	public static ExtentReports getReportObject()
	{
		
		String path = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "ExtentReport.html";
		
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setCSS("body {font-family: Arial, sans-serif;} .some-class {color: blue; }"); 

	//	ExtentSparkReporter reporter= new ExtentSparkReporter("reports/extent-spark-report.html");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().enableOfflineMode(true);
		 reporter.config().setEncoding("UTF-8");
		 
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Sunil Renati");
		return extent;
		
		
	} 

}
