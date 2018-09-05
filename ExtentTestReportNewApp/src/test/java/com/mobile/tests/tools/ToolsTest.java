package com.mobile.tests.tools;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mobile.api.CommonFunctions;
import com.mobile.app.exception.AutomationException;
import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.app.pages.goalsplan.CustomizePlanSettings;
import com.mobile.app.pages.tools.SetTimerPage;
import com.mobile.app.pages.tools.StopWatchPage;
import com.mobile.tests.SetUpTest;

@Test(groups = { "Tools", "Regression" })
public class ToolsTest extends SetUpTest {

	/**
	 * Method to initialize classes
	 */
	public static void intaliazePages() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();

		customizePlanSettings = new CustomizePlanSettings();

		stopWatchPage = new StopWatchPage();

		setTimerPage = new SetTimerPage();

	}

	@Test(groups = "stopWatchUITest", description = "Verfying all the UI on Stop watch")
	public void stopWatchUITest() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		stopWatchPage.selectandVerifyStopWatchOption();

		stopWatchPage.verifyUIObjects();

		CommonFunctions.navigateBack(2);

		dashBoardpage.logout();

	}

	@Test(groups = "testStartPauseResetStopWatch", description = "Verfying Start , Pause and Reset functionalities of Stop Watch")
	public void testStartPauseResetStopWatch() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		stopWatchPage.selectandVerifyStopWatchOption();

		String time = stopWatchPage.startAndPauseStopWatch();

		stopWatchPage.saveVerifyLapList(time, 0);

		Assert.assertTrue(stopWatchPage.resetAllStopWatchData(),
				"Reset functionality is not working");

		CommonFunctions.navigateBack(2);

		dashBoardpage.logout();

	}

	@Test(groups = "testmultipleSaveLapLists", description = "Verfying able to save to mutliple lap lists and Reset to intial")
	public void testmultipleSaveLapLists() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.selectDashboard();

		dashBoardpage.selectingToolsOption();

		stopWatchPage.selectandVerifyStopWatchOption();
		for (int i = 0; i <= 3; i++) {

			String time = stopWatchPage.startAndPauseStopWatch();

			stopWatchPage.saveVerifyLapList(time, i);
		}

		Assert.assertTrue(stopWatchPage.resetAllStopWatchData(),
				"Reset functionality is not working");

		CommonFunctions.navigateBack(2);

		dashBoardpage.logout();

	}

	// sesh shabareesh wr

	@Test(groups = "timerPage", description = "Verfying Start and stop functionalities of Timer")
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
