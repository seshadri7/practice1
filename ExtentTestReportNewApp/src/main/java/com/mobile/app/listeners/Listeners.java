package com.mobile.app.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;
import com.mobile.app.config.PagesHelper;

/**
 * 
 * @author mchavali
 *
 */
public class Listeners implements ISuiteListener, ITestListener {
	public static int totalpassed, totaltcs, totalskipped, totalfailed;
	public static int mastertotalpassed, mastertotaltcs, mastertotalskipped,
			mastertotalfailed;
	static Logger log = Log4jUtil.loadLogger(Listeners.class);
	public static String moduleName;

	public static List<String> failedImagepath = new ArrayList<String>();

	@Override
	public void onStart(ISuite suite) {
		log.info("Listener Before Suite Name start:" + suite.getName());
		totalpassed = 0;
		totaltcs = 0;
		totalskipped = 0;
		totalfailed = 0;
	}

	@Override
	public void onFinish(ISuite suite) {
		log.info("Listener After Suite name ends:" + suite.getName());
		log.info("This is on finish method");
		log.info("Total test cases   :"
				+ (totalpassed + totalskipped + totalfailed));
		log.info("Total passed cases :" + totalpassed);
		log.info("Total failed cases :" + totalfailed);
		log.info("Total skipped cases:" + totalskipped);
		mastertotalpassed += totalpassed;
		mastertotaltcs += totaltcs;
		mastertotalskipped += totalskipped;
		mastertotalfailed += totalfailed;

		// SendEmail.mailGenarate();
	}

	/**
	 * 
	 * For Test Listener related methods
	 */
	@Override
	public void onStart(ITestContext test) {
		log.info("**************Listener Before All test starts:********************"
				+ test.getName());
		moduleName = test.getName();
		if (moduleName.equals("Command line test"))
			moduleName = "";
	}

	@Override
	public void onFinish(ITestContext test) {
		log.info("***********Listener After All test ends:***********************"
				+ test.getName());
	}

	@Override
	public void onTestStart(ITestResult test) {
		totaltcs++;
		log.info("Test method started:" + test.getInstanceName() + "."
				+ test.getName());
	}

	@Override
	public void onTestSuccess(ITestResult test) {
		totalpassed++;
		printTestResults(test);
	}

	@Override
	public void onTestFailure(ITestResult test) {
		totalfailed++;
		printTestResults(test);
		// checkForScreenShot("Failed", test);
	}

	@Override
	public void onTestSkipped(ITestResult test) {
		log.info("Listener If test case skipped:" + test.getName());
		totalskipped++;
		printTestResults(test);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	// This will return method names to the calling function
	public String returnMethodName(ITestNGMethod method) {
		log.info(method.getTestClass().getName() + "." + method.getMethodName());
		return method.getTestClass().getName() + "." + method.getMethodName();
	}

	/*
	 * This is the method which will be executed in case of test pass or fail
	 * This will provide the information on the test
	 */
	@SuppressWarnings("unused")
	private void printTestResults(ITestResult result) {
		String status = null;
		String params = "";
		if (result.getParameters().length != 0) {
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			log.info("Test Method: \"" + result.getName()
					+ "\" had the following parameters: " + params);
		}
	}

	public static String getStatusResult(ITestResult result) {
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		return status;
	}

	/**
	 * 
	 * @param status
	 * @param result
	 */
	public static void checkForScreenShot(String status, ITestResult result) {
		if (status.equals("Failed")) {
			generateScreenShot(result.getName());
		}
	}

	/**
	 * 
	 * @param testName
	 */
	public static String generateScreenShot(String testName) {

		File f = null;
		String path = PagesHelper.failureimagespath + "\\"
				+ new SimpleDateFormat("MM-dd-yyyy").format(new Date()) + "\\"
				+ moduleName + "\\";
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss")
				.format(new Date());
		try {
			File targetfile = new File(path + testName + timestamp + ".png");
			File scrFile = ((TakesScreenshot) Base.driver)
					.getScreenshotAs(OutputType.FILE);
			log.info(path + testName + timestamp + ".png");

			f = new File(path + testName + timestamp + ".png");

			FileUtils.copyFile(scrFile, targetfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			failedImagepath.add(f.getCanonicalPath());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}

	/**
	 * 
	 *
	 * @param status
	 * @param result
	 */
	public static void clickPassedTCSScreenShot(String status,
			ITestResult result) {
		if (status.equals("Pass")) {
			generateScreenShot(result.getName());
		}

	}
}
