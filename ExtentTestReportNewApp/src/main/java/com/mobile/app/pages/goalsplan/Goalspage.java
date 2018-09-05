package com.mobile.app.pages.goalsplan;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class Goalspage extends Base {

	static Logger log = Log4jUtil.loadLogger(Goalspage.class);

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Goal']")
	private WebElement goalsScreenHeaderTitle;

	@AndroidFindBy(id = "com.smartron.sid:id/goto_goalrel")
	private WebElement getActiveGoal;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='walk - jog - run']")
	private WebElement setActiveGoalJogWalkRun;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Get active']")
	private WebElement getActiveGoalJogWalkRunScreen;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Walk - jog - run']")
	private WebElement getActiveGoalJogWalkRunlabel;

	public Goalspage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void setActiveGoalWalkJogRun() {

		log.info("Set Active Goal from goals Screen");

		CommonFunctions.waitforWebElement(driver, getActiveGoal);

		getActiveGoal.click();

		log.info("Clicked on GetActive Goal");

		CommonFunctions.waitforWebElement(driver, goalsScreenHeaderTitle);

		CommonFunctions.waitforWebElement(driver, setActiveGoalJogWalkRun);

		setActiveGoalJogWalkRun.click();

		Assert.assertTrue(getActiveGoalJogWalkRunScreen.isDisplayed(),
				"Jog Walk Run Screen is not displayed");

		Assert.assertTrue(getActiveGoalJogWalkRunlabel.isDisplayed(),
				"Jog Walk Run label is not displayed");

		log.info("Setting up the Activity as Walk Jog Run in Plan");

	}
}
