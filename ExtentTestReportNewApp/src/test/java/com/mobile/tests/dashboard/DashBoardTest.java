package com.mobile.tests.dashboard;

import org.testng.annotations.Test;

import com.mobile.api.CommonFunctions;
import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.app.pages.goalsplan.CustomizePlanSettings;
import com.mobile.app.pages.goalsplan.Goalspage;
import com.mobile.tests.SetUpTest;

@Test(groups = { "DashBoard", "Regression" })
public class DashBoardTest extends SetUpTest {

	/**
	 * Method to initialize classes
	 */
	public static void intaliazePages() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();

		goalPage = new Goalspage();

		customizePlanSettings = new CustomizePlanSettings();
	}

	
	@Test(groups = "dasboardUITest", description = "Verfying all the UI on dashboard", priority = 1)
	public void dasboardUITest() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.verifyUI();

		dashBoardpage.logout();

	}

	@Test(groups = "testToolsOptions", description = "Verifying Tools Options on UI", priority = 0)
	public void testToolsOptions() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		dashBoardpage.goBackToDashborad();

		dashBoardpage.logout();

	}

	@Test(groups = "testJogWalkRunPlan", description = "Verifying Tools Options on UI", priority = 2)
	public void testJogWalkRunPlan() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();
		

		dashBoardpage.selectGoalsPlanOption();

		goalPage.setActiveGoalWalkJogRun();

		customizePlanSettings.clickCustomizePlanButton();

		String remainderTime = customizePlanSettings.planSettings();

		customizePlanSettings.saveandViewPreferences(remainderTime);

		CommonFunctions.navigateBack(3);

		dashBoardpage.viewPlan(remainderTime);

		dashBoardpage.logout();

	}
}
