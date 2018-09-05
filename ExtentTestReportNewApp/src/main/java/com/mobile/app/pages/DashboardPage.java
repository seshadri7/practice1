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

public class DashboardPage extends Base {

	static Logger log = Log4jUtil.loadLogger(DashboardPage.class);

	@AndroidFindBy(xpath = "//android.widget.TextView")
	private WebElement homePageTitle;

	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private WebElement optionsButtonHomeScreen;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='LogOut']")
	private WebElement logOutOption;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='DashBoard']")
	private WebElement dashBoardOption;

	@AndroidFindBy(id = "com.smartron.sid:id/skip_login_button")
	private WebElement skipLoginOption;

	@AndroidFindBy(id = "com.smartron.sid:id/tools_btn")
	private WebElement toolsButton;

	@AndroidFindBy(id = "com.smartron.sid:id/dashboard_actType")
	private WebElement walkingLabel;

	@AndroidFindBy(id = "com.smartron.sid:id/dashboard_act_day")
	private WebElement TodayLabel;

	@AndroidFindBy(id = "com.smartron.sid:id/dashboard_act_stepsCount")
	private WebElement stepsCountLabel;

	@AndroidFindBy(id = "com.smartron.sid:id/dashboard_steps")
	private WebElement stepsLabel;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose a goal and a plan to follow']")
	private WebElement goalPlanOption;

	@AndroidFindBy(className = "android.widget.RelativeLayout")
	private List<WebElement> relativeLayoutScreensonDashboard;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='3']")
	private List<WebElement> layoutScreensonDashboard;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']")
	private List<WebElement> frameLayoutOnToolsDashboard;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Timer']")
	private WebElement toolsTimerOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Goal']")
	private WebElement goalsScreenHeaderTitle;

	@AndroidFindBy(id = "com.smartron.sid:id/view_data")
	private WebElement viewButton;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement OkButtonOnViewDataScreen;

	public DashboardPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	/**
	 * Logout from Application by verifying condition
	 * 
	 */
	public void logout() {

		log.info(" Trying to logout from the application ");

		CommonFunctions.waitforWebElement(driver, optionsButtonHomeScreen);

		optionsButtonHomeScreen.click();

		CommonFunctions.waitforWebElement(driver, logOutOption);

		logOutOption.click();

		CommonFunctions.waitforWebElement(driver, skipLoginOption);

		log.info("Succesfully logout from the application");

	}

	public void selectDashboard() {

		log.info("Selecting Dashbord from menu items");

		CommonFunctions.waitforWebElement(driver, optionsButtonHomeScreen);

		optionsButtonHomeScreen.click();

		CommonFunctions.waitforWebElement(driver, dashBoardOption);

		dashBoardOption.click();

		LoginPage.verifyHeaderTitle(LOGIN_CONFIRMATION);

		log.info("Selected Dashboard screen from Menu");

	}

	public void selectingToolsOption() {

		CommonFunctions.waitforWebElement(driver, toolsButton);

		toolsButton.click();

		Assert.assertTrue(toolsTimerOption.isDisplayed(),
				"Timer option is not displayed on UI");

		log.info("======>>>>>>Selected tools option<<<<<<=========");

	}

	public void goBackToDashborad() {

		CommonFunctions.navigateBack(1);

	}

	public void verifyUI() {

		log.info("Verifying the UI elements on the Page ==");

		log.info("Number of Realtive layouts found on page ===>   "
				+ relativeLayoutScreensonDashboard.size());

		if (relativeLayoutScreensonDashboard.size() > 0) {

			log.info("Number of Linear layouts found on page ===>   "
					+ layoutScreensonDashboard.size());

			if (layoutScreensonDashboard.size() > 0) {
				CommonFunctions.waitforWebElement(driver, TodayLabel);

				Assert.assertTrue(stepsLabel.isDisplayed(),
						"Element is not displayed on UI");

				Assert.assertTrue(stepsCountLabel.isDisplayed(),
						"Element is not displayed on UI");

				Assert.assertTrue(walkingLabel.isDisplayed(),
						"Element is not displayed on UI");

				Assert.assertTrue(TodayLabel.isDisplayed(),
						"Element is not displayed on UI");

				Assert.assertTrue(toolsButton.isDisplayed(),
						"Element is not displayed on UI");

				Assert.assertTrue(goalPlanOption.isDisplayed(),
						"Element is not displayed on UI");

				log.info("===========Verified all the UI elements are displayed===============");

			}

		}

	}

	public void selectGoalsPlanOption() {

		log.info("+++ ===>>>>>Customization of plan<<<<<====== +++");

		CommonFunctions.waitforWebElement(driver, goalPlanOption);

		goalPlanOption.click();

		log.info("Goals Screen");

		CommonFunctions.waitforWebElement(driver, goalsScreenHeaderTitle);

	}

	public void viewPlan(String remainderTime) {

		log.info("Viewing the plan date and Time ");

		CommonFunctions.waitforWebElement(driver, viewButton);

		viewButton.click();

		WebElement text = driver.findElement(By.id("android:id/text1"));

		String data = text.getText();

		Assert.assertTrue(data.contains(remainderTime),
				"There is miss match in the remainder time with Viewed data");

		String splitingData[] = data.split(" : ");

		for (String SpilltedData : splitingData) {

			System.out.println("Seshu and Shabreshh ==>>>>   " + SpilltedData);

		}

		CommonFunctions.waitforWebElement(driver, OkButtonOnViewDataScreen);

		OkButtonOnViewDataScreen.click();
	}
}
