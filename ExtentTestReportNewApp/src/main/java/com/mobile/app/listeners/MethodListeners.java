package com.mobile.app.listeners;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.mobile.api.Log4jUtil;



/**
 *
 * @author mchavali
 *
 */
public class MethodListeners implements IInvokedMethodListener {
	static Logger log = Log4jUtil.loadLogger(MethodListeners.class);

	/*
	 * This belongs to IInvokedMethodListener and will execute before every
	 * method including @Before @After @Test
	 */
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {
		String textMsg = "Started execution of the method: \""
				+ returnMethodName(arg0.getTestMethod());
		log.info(textMsg + "\"");
	} 

	/*
	 * This belongs to IInvokedMethodListener and will execute after every
	 * method including @Before @After @Test
	 */
	public void afterInvocation(IInvokedMethod arg0, ITestResult result) {
		String status = Listeners.getStatusResult(result);
		String textMsg = "Status of the method: \""
				+ returnMethodName(arg0.getTestMethod());
		log.info(textMsg + "\" is:" + status);
		Listeners.checkForScreenShot(status, result);
		// Listeners.clickPassedTCSScreenShot(status, result);
	}

	// This will return method names to the calling function
	public String returnMethodName(ITestNGMethod method) {
		// System.out.println(method.getTestClass().getName()+"."+method.getMethodName());
		return method.getTestClass().getName() + "." + method.getMethodName();
	}
}
