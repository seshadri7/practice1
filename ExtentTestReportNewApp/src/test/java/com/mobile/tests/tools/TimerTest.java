package com.mobile.tests.tools;

import org.testng.annotations.Test;

import com.mobile.app.exception.AutomationException;
import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.app.pages.goalsplan.CustomizePlanSettings;
import com.mobile.app.pages.tools.SetTimerPage;
import com.mobile.tests.SetUpTest;

@Test(groups = "Tools")
public class TimerTest extends SetUpTest {

	public static void intaliazePages() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();

		customizePlanSettings = new CustomizePlanSettings();

		setTimerPage = new SetTimerPage();

	}

	@Test(groups = "VerifyFisrtScript")
	public void verifyFisrtScript() throws AutomationException {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		setTimerPage.selectingTmerOption();

		setTimerPage.uiFunction();

		setTimerPage.enterTimerValue();

	}

	@Test(groups = "timerPage1", description = "Verfying Start and stop functionalities of Timer")
	public void testStartTimer() throws AutomationException {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		setTimerPage.selectingTmerOption();

		setTimerPage.uiFunction();

		setTimerPage.enterTimerValue();

	}
}
