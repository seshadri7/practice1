package com.mobile.app.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class LoginPage extends Base {

	static Logger log = Log4jUtil.loadLogger(LoginPage.class);

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='my_text_fieldCD']")
	private WebElement homeTextField;

	@AndroidFindBy(id = "com.smartron.sid:id/email_address")
	private WebElement username;

	@AndroidFindBy(id = "com.smartron.sid:id/password")
	private WebElement password;

	@AndroidFindBy(id = "com.smartron.sid:id/login")
	private WebElement submit;

	@AndroidFindBy(id = "com.smartron.sid:id/skip_login_button")
	private WebElement skipLoginOption;

	@AndroidFindBy(xpath = "//android.widget.TextView")
	private static WebElement homePageTitle;

	@AndroidFindBy(id = "com.smartron.sid:id/forgot_ontextview")
	private WebElement forgotPassword;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='This app needs location access']")
	private static List<WebElement> allowAcesspopUp;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private static List<WebElement> allowLocationAcesspopUp;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Open settings']")
	private static List<WebElement> screenOverlayButton;
	
	

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private static List<WebElement> screenOverlayFunctionalityOKButton;

	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * 
	 * @param userName
	 * @param passWord
	 */
	public void loginasUser(String userName, String passWord) {

		log.info("Login into Application===========>> ");

		/* username and password is not passing for time being */

		/*
		 * enterUserName(userName);
		 * 
		 * enterPassword(passWord);
		 * 
		 * submit();
		 */

		skipLogin();

		verifyHeaderTitle(LOGIN_CONFIRMATION);

	}

	/**
	 * Method to verify Login
	 * 
	 * @param loginConfirmation
	 */
	public static void verifyHeaderTitle(String loginConfirmation) {

		CommonFunctions.waitforWebElement(driver, homePageTitle);

		verifyLocationAccess();

		verfiyScreenOverlay();

		Assert.assertTrue(
				homePageTitle.getText().equalsIgnoreCase(loginConfirmation),
				"Title mismatched    Actaul -->    " + homePageTitle.getText()
						+ " <----  but Expected -->    " + loginConfirmation
						+ ")");
	}

	private static void verfiyScreenOverlay() {

		log.info("Found Screen Over lay Pop up  " + screenOverlayButton.size()
				+ " buttons.");

		if (screenOverlayButton.size() > 0) {

			screenOverlayButton.get(0).click();

			CommonFunctions.navigateBack(1);

			if (screenOverlayFunctionalityOKButton.size() > 0) {

				log.info("Allow Functionality ==>>");

				screenOverlayFunctionalityOKButton.get(0).click();

			}
		} else {

			log.info("There is no pop up on Location Access providers");
		}

	}

	/**
	 * 
	 * @param userNameValue
	 */

	private void enterUserName(String userNameValue) {

		log.info("Enter Username ===========>> ");

		CommonFunctions.enterTextInInputBox(driver, username, userNameValue);

	}

	/**
	 * 
	 * @param passWordValue
	 */
	private void enterPassword(String passWordValue) {

		log.info("Enter Password ===========>> ");

		CommonFunctions.enterTextInInputBox(driver, password, passWordValue);

		driver.hideKeyboard();

	}

	/**
	 * Submit the infrmation
	 */
	private void submit() {

		log.info("Click Submit button to login ===========>> ");

		CommonFunctions.waitforWebElement(driver, submit);

		submit.click();

	}

	/**
	 * Method to Skip Login
	 */

	public void skipLogin() {

		log.info("Click Skip login button to login ===========>> ");

		CommonFunctions.waitforWebElement(driver, skipLoginOption);

		skipLoginOption.click();

		verifyHeaderTitle(LOGIN_CONFIRMATION);
	}

	/**
	 * Method to click forgot Password
	 */
	public void selectForgotPassword() {

		log.info("Click forgot password button  ===========>> ");

		CommonFunctions.waitforWebElement(driver, forgotPassword);

		forgotPassword.click();

	}

	private static void verifyLocationAccess() {

		log.info("Found " + allowAcesspopUp.size() + " buttons.");

		if (allowAcesspopUp.size() > 0) {

			driver.findElement(By.id("android:id/button1")).click();

			if (allowLocationAcesspopUp.size() > 0) {

				log.info("Allow Location Access");

				allowLocationAcesspopUp.get(0).click();

			}
		} else {

			log.info("There is no pop up on Location Access providers");
		}
	}
}