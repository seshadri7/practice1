package com.mobile.app.pages.goalsplan;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class CustomizePlanSettings extends Base {

	static Logger log = Log4jUtil.loadLogger(CustomizePlanSettings.class);

	@AndroidFindBy(id = "com.smartron.sid:id/customise_plan")
	private WebElement customizePlanButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Plan Settings']")
	private WebElement planSettingScreen;

	@AndroidFindBy(id = "com.smartron.sid:id/rem_togglebtn")
	private WebElement remainderToggleButton;

	@AndroidFindBy(id = "com.smartron.sid:id/reminder_time")
	private WebElement setremainderTime;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private WebElement clockOkButton;

	@AndroidFindBy(id = "com.smartron.sid:id/gps_tracktoggle")
	private WebElement GPSToggleButton;

	@AndroidFindBy(id = "com.smartron.sid:id/save_pref")
	private WebElement savePreferencesButton;

	@AndroidFindBy(id = "com.smartron.sid:id/dismiss_benchmark")
	private WebElement dismissBenchmarkButton;

	@AndroidFindBy(id = "com.smartron.sid:id/view_planbtn")
	private WebElement viewPlanButton;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement viewOkButton;

	public CustomizePlanSettings() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void clickCustomizePlanButton() {

		log.info("Clicking Customize Plan button");

		CommonFunctions.scrollToDimensions(driver, 2);

		// TestBase.scrollToDimensions(driver);

		CommonFunctions.waitforWebElement(driver, customizePlanButton);

		customizePlanButton.click();

	}

	public String planSettings() {

		log.info("Setting up the plan");

		CommonFunctions.waitforWebElement(driver, planSettingScreen);

		CommonFunctions.waitforWebElement(driver, remainderToggleButton);

		CommonFunctions.waitforWebElement(driver, GPSToggleButton);

		remainderToggleButton.click();

		CommonFunctions.waitforWebElement(driver, setremainderTime);

		setremainderTime.click();

		CommonFunctions.waitforWebElement(driver, clockOkButton);

		clockOkButton.click();

		String timevalue = setremainderTime.getText();

		GPSToggleButton.click();

		log.info("Entered all required fields for plan");

		return timevalue;

	}

	public void saveandViewPreferences(String timeValue) {

		CommonFunctions.scrollToDimensions(driver, 1);

		CommonFunctions.waitforWebElement(driver, savePreferencesButton);

		savePreferencesButton.click();

		CommonFunctions.scrollToDimensions(driver, 1);

		CommonFunctions.waitforWebElement(driver, dismissBenchmarkButton);

		dismissBenchmarkButton.click();

		log.info("Saved the Plan Preferences");

		CommonFunctions.navigateBack(1);

		CommonFunctions.scrollToDimensions(driver, 2);

		// TestBase.scrollToDimensions(driver);

		CommonFunctions.waitforWebElement(driver, viewPlanButton);

		viewPlanButton.click();

		String text = driver.findElement(By.id("android:id/text1")).getText();

		log.info(timeValue);

		System.out.println("===========>>> Time value +++   " + timeValue);

		System.out.println("===========>>> View Plan Data +++   " + text);

		String[] values = text.split(" : ");

		for (String val : values) {

			if (val.contains("Gps_Tracker")) {

				System.out.println("===>> To Get Time" + val);

				System.out.println("Value v found ------------>>>+++ LEE "
						+ val.trim().substring(0, 5));

				if (val.trim().substring(0, 5)
						.equalsIgnoreCase(timeValue.trim())) {
					System.out.println("Passed");
				}
			}

			System.out.println("===>>" + val);
		}

		if (text.contains(timeValue)) {
			log.info("Passed");
		}

		CommonFunctions.waitforWebElement(driver, viewOkButton);

		viewOkButton.click();

	}
}
