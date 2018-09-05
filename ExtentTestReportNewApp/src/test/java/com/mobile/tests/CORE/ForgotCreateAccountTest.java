package com.mobile.tests.CORE;

import org.testng.annotations.Test;

import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.ForgotPasswordPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.tests.SetUpTest;

@Test(groups = { "Login", "Regression" })
public class ForgotCreateAccountTest extends SetUpTest {

	@Test(groups = "testForgotPassword")
	public void testForgotPassword() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();

		forgotPasswordPage = new ForgotPasswordPage();

		loginPage.selectForgotPassword();

		forgotPasswordPage.submitForgotUser("Test@qa.com");

	}

}
