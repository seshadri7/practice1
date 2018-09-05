package com.mobile.app.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;
import com.mobile.app.email.SendEmail;

/**
 * 
 * @author mchavali
 *
 */
public class ExtentTestNGIReporterListener implements IReporter {

	static Logger log = Log4jUtil.loadLogger(Base.class);

	private static final String FILE_NAME = "MobileExtentResults.html";

	private ExtentReports extent;

	private int numberOfPassedTests = 0, numberOfFailedTests = 0,
			numberOfSkippedTests = 0;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {

		log.info("Executing Extent Test Results");

		init(outputDirectory);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);

				numberOfPassedTests = context.getPassedTests().size();

				numberOfFailedTests = context.getFailedTests().size();

				numberOfSkippedTests = context.getSkippedTests().size();

			}

			for (String s : Reporter.getOutput()) {

				extent.setTestRunnerOutput(s);
			}

			extent.flush();

			SendEmail.mailGenarate(numberOfPassedTests, numberOfFailedTests,
					numberOfSkippedTests);

		}
	}

	private void init(String outputDirectoryPath) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				outputDirectoryPath + File.separator + FILE_NAME);

		log.info("Extent HTML Reporter Path ==>> " + outputDirectoryPath
				+ File.separator + FILE_NAME);

		htmlReporter.config().setDocumentTitle(
				"ExtentReports -  Created by C-MuraliKrishna");

		htmlReporter.config().setReportName(
				"ExtentReports -  Created by C-MuraliKrishna");

		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);

		htmlReporter.config().setTheme(Theme.STANDARD);

		htmlReporter.config().setChartVisibilityOnOpen(true);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setReportUsesManualConfiguration(true);
	}

	private void buildTestNodes(IResultMap tests, Status status) {

		ExtentTest test;

		if (tests.size() > 0) {

			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());

					log.info("  Fail Section============>>  +      " + status
							+ "   Test Name ==>        "
							+ result.getMethod().getMethodName());

					try {

						for (String iterable_element : Listeners.failedImagepath) {

							if (iterable_element.contains(result.getMethod()
									.getMethodName())) {

								test.addScreenCaptureFromPath(iterable_element);

								log.info("Attached Screenshots == >> for failed cases");

							}

						}

					} catch (IOException e) {

						e.printStackTrace();
					}

				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
					log.info("  Pass Section============>>  +      " + status
							+ "   Test Name ==>        "
							+ result.getMethod().getMethodName());
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));

				test.getModel().setEndTime(getTime(result.getEndMillis()));

			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}