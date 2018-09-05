package com.mobile.tests.CORE;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mobile.api.ReadXls;
import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.tests.SetUpTest;

@Test(groups = { "Login", "Regression" })
public class LoginTest extends SetUpTest {

	/**
	 * Method to initialize classes
	 */
	public static void intaliazePages() {

		dashBoardpage = new DashboardPage();

		loginPage = new LoginPage();
	}

	@Test(groups = "loginTestWithValidData")
	public void loginTestWithValidData() {

		intaliazePages();

		loginPage.loginasUser(getUsername(), getPassword());

		dashBoardpage.logout();

	}

	@Test(groups = "loginTestWithSkipLogin")
	public void loginTestWithSkipLogin() {
		intaliazePages();

		loginPage.skipLogin();

		dashBoardpage.logout();
	}

	@Test(groups = "loginTestWithInValidData")
	public void loginTestWithInValidData() {

		intaliazePages();

		loginPage.loginasUser("12345@3556@1234.com", "qa");

	}

	@Test(dataProvider = "Muralee1", groups = "testxls")
	public void testdbRet(Map<String, String> testdata) {

		String accidentState = testdata.get("username"), policyNumber = testdata
				.get("Password");

		System.out.println(accidentState);

		System.out.println(policyNumber);

		System.out.println("Muralee =========>> Testing " + testdata.values());

	}

	@DataProvider(name = "Muralee1")
	public Object[][] testqa() {

		return ReadXls.getSpecifiedRowDataAsMapObject(
				"./src/test/resources/Abc.xlsx", "Testing", 2);

	}
}
