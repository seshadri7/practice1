package com.mobile.app.pages.tools;

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
import com.mobile.app.config.PagesHelper;

public class StopWatchPage extends Base implements PagesHelper {
	static Logger log = Log4jUtil.loadLogger(StopWatchPage.class);

	@AndroidFindBy(id = "com.smartron.sid:id/goto_Stopwatch")
	private WebElement stopWatchOptionButton;

	@AndroidFindBy(id = "com.smartron.sid:id/start_stopwatch")
	private WebElement startStopWatchButton;

	@AndroidFindBy(id = "com.smartron.sid:id/pause_stopwatch")
	private WebElement pauseStopWatchButton;

	@AndroidFindBy(id = "com.smartron.sid:id/savelap")
	private WebElement saveLapButton;

	@AndroidFindBy(id = "com.smartron.sid:id/reset_stopwatch")
	private WebElement resetStopWatchButton;

	@AndroidFindBy(id = "com.smartron.sid:id/lap_list")
	private WebElement savedLapList;

	private String lapLists = "//android.widget.TextView[contains(@resource-id,'android:id/text1') and @index='REPLACE']";

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'android:id/text1') and @index='0']")
	private List<WebElement> savedLapList1;

	@AndroidFindBy(id = "com.smartron.sid:id/textView")
	private WebElement stopWatchTime;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='LogOut']")
	private WebElement logOutOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='00:00:00']")
	private WebElement intailStartUpStopWatchData;

	@AndroidFindBy(xpath = "//android.widget.TextView")
	private static WebElement pageTitle;

	@AndroidFindBy(id = "com.smartron.sid:id/decor_content_parent")
	private List<WebElement> relativeLayoutScreensonStopWatch;

	public StopWatchPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void selectandVerifyStopWatchOption() {

		log.info("Selecting Stop Watch Option");

		CommonFunctions.waitforWebElement(driver, stopWatchOptionButton);

		stopWatchOptionButton.click();

		Assert.assertTrue(
				pageTitle.getText().equalsIgnoreCase(
						STOPWATCH_TITLE_CONFIRMATION),
				"Title mismatched    Actaul -->    " + pageTitle.getText()
						+ " <----  but Expected -->    "
						+ STOPWATCH_TITLE_CONFIRMATION + ")");

	}

	public void verifyUIObjects() {

		log.info("Verifying the UI elements on the Page ==");

		log.info("Number of Linear layouts found on page ===>   "
				+ relativeLayoutScreensonStopWatch.size());

		if (relativeLayoutScreensonStopWatch.size() > 0) {
			CommonFunctions.waitforWebElement(driver, startStopWatchButton);

			Assert.assertTrue(startStopWatchButton.isDisplayed(),
					"Element is not displayed on UI");

			Assert.assertTrue(resetStopWatchButton.isDisplayed(),
					"Element is not displayed on UI");

			Assert.assertTrue(pauseStopWatchButton.isDisplayed(),
					"Element is not displayed on UI");

			Assert.assertTrue(intailStartUpStopWatchData.isDisplayed(),
					"Element is not displayed on UI");

			Assert.assertTrue(savedLapList.isDisplayed(),
					"Element is not displayed on UI");

			Assert.assertTrue(saveLapButton.isDisplayed(),
					"Element is not displayed on UI");

			log.info("===========Verified all the UI elements are displayed===============");

		}

	}

	public String startAndPauseStopWatch() {

		CommonFunctions.waitforWebElement(driver, startStopWatchButton);

		startStopWatchButton.click();

		log.info("Started the Stop watch .............");

		log.info("pause now");

		driver.findElement(By.xpath("//android.widget.Button[@index='2']"))
				.click();

		log.info(".......Paused the Stop watch .............");

		String timeElapsed = stopWatchTime.getText();

		System.out.println(timeElapsed);

		return timeElapsed;

	}

	public void saveVerifyLapList(String time1, int i) {

		String replacedValue = lapLists.replace("REPLACE", Integer.toString(i));

		System.out.println(replacedValue);

		CommonFunctions.waitforWebElement(driver, saveLapButton);

		saveLapButton.click();

		CommonFunctions.waitforWebElement(driver, savedLapList);

		WebElement lapListValue = driver.findElement(By.xpath(replacedValue));

		CommonFunctions.waitforWebElement(driver, lapListValue);

		System.out.println(lapListValue.getText());

		Assert.assertEquals(time1, lapListValue.getText(),
				"Both are not equal== Actual ==>>> " + time1
						+ "Expected -===>> " + lapListValue.getText());

	}

	public boolean resetAllStopWatchData() {

		CommonFunctions.waitforWebElement(driver, resetStopWatchButton);

		resetStopWatchButton.click();

		Assert.assertTrue(intailStartUpStopWatchData.isDisplayed(),
				"Intial position is not displayed");

		if (savedLapList1.size() > 0) {

			log.info("Still the date is not cleared ");
			CommonFunctions.waitforPageLoad(1);
			return false;
		}

		else {

			log.info("All the data reset");
			CommonFunctions.waitforPageLoad(1);
			return true;
		}

	}
}
