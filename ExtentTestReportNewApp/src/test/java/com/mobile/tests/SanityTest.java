package com.mobile.tests;

import org.testng.annotations.Test;

import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.ForgotPasswordPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.app.pages.goalsplan.CustomizePlanSettings;
import com.mobile.app.pages.goalsplan.Goalspage;
import com.mobile.app.pages.tools.NotificationsPage;
import com.mobile.app.pages.tools.SetAlarmPage;
import com.mobile.app.pages.tools.SetTimerPage;
import com.mobile.app.pages.tools.StopWatchPage;

public class SanityTest extends SetUpTest {

	/**
	 * Method to initialize classes
	 */
	public static void intaliazePages() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();

		goalPage = new Goalspage();

		forgotPasswordPage = new ForgotPasswordPage();

		customizePlanSettings = new CustomizePlanSettings();

		setAlarmPage = new SetAlarmPage();

		notificationsPage = new NotificationsPage();

		setTimerPage = new SetTimerPage();

		stopWatchPage = new StopWatchPage();
	}

	@Test(groups = "loginTestWithValidData")
	public void loginTestWithValidData() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.logout();

	}

	@Test(groups = "testForgotPassword")
	public void testForgotPassword() {

		loginPage.selectForgotPassword();

		forgotPasswordPage.submitForgotUser("Test@qa.com");

	}
}
