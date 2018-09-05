/**
 * 
 */
package com.mobile.tests;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mobile.app.config.Base;
import com.mobile.app.config.TestBase;
import com.mobile.app.pages.SetUpFlow;

/**
 * @author Smartron
 *
 */
@Test(groups = { "setUpUIABC", "Regression" })
public class SetUpTest extends TestBase {

	@BeforeTest(alwaysRun = true)
	public void setup() throws MalformedURLException {

		Base.intailizeApp();

	}

	@Test(testName = "testStartScreenUI", description = "Verify UI and Elements on Start t.health Scren")
	public void testStartScreenUI() {

		SetUpFlow st = new SetUpFlow();

		st.StartUIOnTHealth();

	}

	@Test(testName = "testRefreshUnderFoundScreen", description = "Refreshing the BT under Bands found screen")
	public void testRefreshUnderFoundScreen() {

		SetUpFlow st = new SetUpFlow();

		st.StartUIOnTHealth();

		st.clickStartOption();

		st.allowLocationConnectionOfDevice();

		st.searchBTscreen();

		st.refreshButtonClickUnderTbandFoundScreen();

		st.searchBTscreen();

	}

	@Test(testName = "testDifferentbandSelectionUnderPairScreen", description = "Select different band option under pair screen")
	public void testDifferentbandSelectionUnderPairScreen() {

		SetUpFlow st = new SetUpFlow();

		st.StartUIOnTHealth();

		st.clickStartOption();

		st.allowLocationConnectionOfDevice();

		st.searchBTscreen();

		st.selectFirstband();

		st.refreshButtonClickUnderTbandFoundScreen();

		st.searchBTscreen();

		st.selectFirstband();

		st.pairWithBand();

	}

	@Test(testName = "testSetUPTillPairingScreen", description = "Verify Navigation and Screen pairing code")
	public void testSetUPTillPairingScreen() {

		SetUpFlow st = new SetUpFlow();

		st.StartUIOnTHealth();

		st.clickStartOption();

		st.allowLocationConnectionOfDevice();

		st.searchBTscreen();

		st.selectFirstband();

		st.pairWithBand();
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		TestBase.killBrowserSession();
	}

}
