package com.mobile.app.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class ForgotPasswordPage extends Base {

	static Logger log = Log4jUtil.loadLogger(ForgotPasswordPage.class);

	@AndroidFindBy(id = "com.smartron.sid:id/forgot_passsubmit")
	private WebElement forgotPasswordSubmit;

	@AndroidFindBy(id = "com.smartron.sid:id/forgot_email")
	private WebElement forgotMailId;

	public ForgotPasswordPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to submit forgot Password email
	 * 
	 * @param username
	 */
	public void submitForgotUser(String username) {

		log.info("Enter email id of the user ");

		CommonFunctions.enterTextInInputBox(driver, forgotMailId, username);

		CommonFunctions.waitforWebElement(driver, forgotPasswordSubmit);

		forgotPasswordSubmit.click();

		log.info("Submiited the data");
	}
}
