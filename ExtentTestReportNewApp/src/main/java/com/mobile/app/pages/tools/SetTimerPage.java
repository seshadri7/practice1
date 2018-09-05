package com.mobile.app.pages.tools;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;
import com.mobile.app.exception.AutomationException;

public class SetTimerPage extends Base {
	static Logger log = Log4jUtil.loadLogger(StopWatchPage.class);

	@AndroidFindBy(id = "com.smartron.sid:id/LinearLayout1")
	private WebElement timerLinearLayout;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='00:00']")
	private WebElement textView;

	@AndroidFindBy(id = "com.smartron.sid:id/goto_Timer")
	private WebElement timerButton;

	@AndroidFindBy(id = "com.smartron.sid:id/edtTimerValue")
	private WebElement editTextBox;

	@AndroidFindBy(id = "com.smartron.sid:id/btnStartTime")
	private WebElement startButton;

	@AndroidFindBy(id = "com.smartron.sid:id/btnStopTime")
	private WebElement stopButton;

	public SetTimerPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void enterTimerValue() {

		CommonFunctions.waitforWebElement(driver, editTextBox);

		CommonFunctions.enterTextInInputBox(driver, editTextBox, "1");

		driver.hideKeyboard();

		startButton.click();

		CommonFunctions.waitforPageLoad(2);

		CommonFunctions.waitforWebElement(driver, stopButton);

		stopButton.click();

	}

	public void selectingTmerOption() throws AutomationException{
		CommonFunctions.waitforWebElement(driver, timerButton);

		timerButton.click();
		
		timerButton.sendKeys("Teste");

	}

	public void uiFunction() {

		if (timerLinearLayout.isDisplayed()) {

			Assert.assertTrue(textView.isDisplayed(), "00:00 is not displayed");

			Assert.assertTrue(editTextBox.isDisplayed(),
					"edit box is not displayed");
			Assert.assertTrue(startButton.isDisplayed(),
					"Start button is not displayed");

		}

	}

}
