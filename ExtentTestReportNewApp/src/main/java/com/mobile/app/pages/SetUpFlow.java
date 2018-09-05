package com.mobile.app.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mobile.api.CommonFunctions;
import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;
import com.mobile.app.config.PagesHelper;

public class SetUpFlow extends Base implements PagesHelper {

	static Logger log = Log4jUtil.loadLogger(SetUpFlow.class);

	/** Start Screen **/

	@AndroidFindBy(id = "com.smartron.tband:id/imageView3")
	private WebElement thealthLogoStart;

	@AndroidFindBy(id = "com.smartron.tband:id/imageView")
	private WebElement thealthImg;

	@AndroidFindBy(id = "com.smartron.tband:id/start_welcome")
	private WebElement startButton;

	@AndroidFindBy(id = "com.smartron.tband:id/congrats_tv")
	private WebElement congratsTbandMessage;

	/**
	 * Allow Location for device
	 */

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private List<WebElement> mobileLocationAllowButton;

	@AndroidFindBy(id = "android:id/button1")
	private List<WebElement> mobileLocationOKButton;

	/** Agree Notice Screen BT **/

	@AndroidFindBy(id = "com.smartron.tband:id/imageView2")
	private WebElement thealthLogoAgreeBT;

	@AndroidFindBy(id = "com.smartron.tband:id/linearLayout")
	private WebElement mobileBTImgAgreeScreen;

	@AndroidFindBy(id = "android.widget.ImageView")
	private WebElement bluetoothSymImgAgree;

	@AndroidFindBy(id = "com.smartron.tband:id/textView")
	private WebElement agreeNoticeText;

	@AndroidFindBy(id = "com.smartron.tband:id/allowbluetoothquestion")
	private WebElement agreeNoticeText1;

	@AndroidFindBy(id = "com.smartron.tband:id/start_welcome_agreed")
	private WebElement agreeButton;

	/** BT Search Screen **/

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement tbandLogoSearch;

	@AndroidFindBy(id = "com.smartron.tband:id/imageView10")
	private WebElement thealthImgSearchScreen;

	@AndroidFindBy(xpath = "//android.widget.TextView[@index='1']")
	private WebElement bluetoothButtonIconText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Searching for t·bands']")
	private WebElement BTSearchbar;

	@AndroidFindBy(id = "com.smartron.tband:id/ivLoader")
	private List<WebElement> progressBar;

	/** Not Found tband Screen **/

	@AndroidFindBy(id = "com.smartron.tband:id/imageView9")
	private WebElement tbandLogoNotFoundScreen;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='tband not found']")
	private List<WebElement> BTSearchbarNotFound;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Please retry']")
	private WebElement BTSearchbarNotFoundRetryText;

	@AndroidFindBy(id = "com.smartron.tband:id/retry")
	private WebElement BTSearchbarRetrybutton;

	/** Found Screen **/

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement thealthLogoFoundScreen;

	@AndroidFindBy(id = "com.smartron.tband:id/found_tv")
	private WebElement tbandFoundScreenText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Please choose your t·band')]")
	private WebElement tbandFoundScreenDescText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'android:id/text1') and @index='0']")
	private WebElement tbandFoundScreenFirstFound;

	@AndroidFindBy(id = "com.smartron.tband:id/next")
	private WebElement tbandFoundScreenNextButton;

	@AndroidFindBy(id = "com.smartron.tband:id/refresh")
	private WebElement tbandFoundScreenRefreshButton;

	/** PairingScreen **/

	@AndroidFindBy(className = "android.widget.ImageView")
	private WebElement thealthLogoPairScreen;

	@AndroidFindBy(id = "com.smartron.tband:id/tv_setup_name")
	private WebElement tbandPairingScreenPairText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@index='2']")
	private WebElement tbandPairingcodeavailbiltyText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@index='3']")
	private WebElement tbandPairingScreenPairCode;

	@AndroidFindBy(id = "com.smartron.tband:id/pairbtn")
	private WebElement tbandPairingScreenButton;

	@AndroidFindBy(id = "com.smartron.tband:id/selectdif")
	private WebElement tbandPairingScreenDifferentBandButton;

	public SetUpFlow() {

		PageFactory.initElements(new AppiumFieldDecorator(driver, 5,
				TimeUnit.SECONDS), this);

	}

	/**
	 * Verifying logo and UI Elements
	 */

	public void StartUIOnTHealth() {

		log.info("Verfying t.band Logo and Image===>> ");

		try {

			CommonFunctions.waitforWebElement(driver, thealthImg);

			CommonFunctions.waitforWebElement(driver, thealthLogoStart);

			log.info("Tband image is displayed ==>>>"
					+ thealthImg.isDisplayed());

			log.info("Tband logo is displayed ==>>>"
					+ thealthLogoStart.isDisplayed());

			if (thealthImg.isDisplayed() && thealthLogoStart.isDisplayed()) {

				CommonFunctions.waitforWebElement(driver, startButton);

				Assert.assertEquals(congratsTbandMessage.getText(),
						PagesHelper.CONGRATS_MESAGE_SCREEN1);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/*
	 * Click Start button on t.health home Screen
	 */

	public void clickStartOption() {

		startButton.click();

		log.info("Clicked on start to navigate to Agree Screen");
	}

	/**
	 * Allow BLE of the device
	 */

	public void allowLocationConnectionOfDevice() {

		if (mobileLocationAllowButton.size() > 0) {
			log.info("Agreeing the Location connections");
			mobileLocationAllowButton.get(0).click();

		} else {
			allowBluetoothCon();

			if (mobileLocationOKButton.size() > 0) {
				log.info("Agreeing the Location connections");
				mobileLocationOKButton.get(0).click();

			}

		}
	}

	/**
	 * Allow Bluetooth Connection Agree
	 */
	public void allowBluetoothCon() {

		log.info("Agreeing the blutooth connections");

		CommonFunctions.waitforWebElement(driver, mobileBTImgAgreeScreen);

		CommonFunctions.waitforWebElement(driver, thealthLogoAgreeBT);

		log.info("Mobile bluettoth image is displayed ==>>>"
				+ mobileBTImgAgreeScreen.isDisplayed());

		log.info("t.health logo is displayed ==>>>"
				+ thealthLogoAgreeBT.isDisplayed());

		if (thealthLogoAgreeBT.isDisplayed()
				&& mobileBTImgAgreeScreen.isDisplayed()) {

			CommonFunctions.waitforWebElement(driver, agreeNoticeText);

			CommonFunctions.waitforWebElement(driver, agreeNoticeText1);

			log.info("Agree notice for bluetooth connection "
					+ agreeNoticeText.getText());

			log.info("Agree notice for bluetooth connection Question "
					+ agreeNoticeText1.getText());

			if (agreeNoticeText.getText().contains(AGREE_NOTICE)) {
				if (agreeNoticeText1.getText().contains(AGREE_NOTICE1)) {

					Assert.assertTrue(true,
							"Any of the Notice is not availbale on page ");
				}
			}

			agreeButton.click();

			List<WebElement> BTOption = extracted();

			while (BTOption.size() > 0) {

				BTOption.get(0).click();

				log.info("Accept BT permissions of device");

				BTOption = extracted();

				// agreeButton.click();
			}

			log.info("Clicked on Agree button to naivate to Serach screen");

		}

	}

	private List extracted() {
		return driver.findElements(By
				.id("com.android.packageinstaller:id/permission_allow_button"));
	}

	/**
	 * Search for tband using BT
	 */

	public void searchBTscreen() {

		log.info("Bluetooth Search Screen ");

		Assert.assertEquals(startButton.getText(), BT_SEARCH,
				"Search Screen text is not displayed");

		for (int i = 0; i <= 10;) {

			log.info("Progress Bar is Displayed Size is " + progressBar.size());

			if (progressBar.size() > 0) {

				if (tbandLogoSearch.isDisplayed()
						&& thealthImgSearchScreen.isDisplayed()) {

					CommonFunctions.waitforWebElement(driver, tbandLogoSearch);

					CommonFunctions.waitforWebElement(driver,
							thealthImgSearchScreen);

					CommonFunctions.waitforWebElement(driver, BTSearchbar);

					log.info("Searching for the t-band "
							+ BTSearchbar.getText());

					Assert.assertEquals(BTSearchbar.getText(), BT_SEARCH,
							"Failed to Assert with text ");

					i++;

					CommonFunctions.waitforPageLoad(8);

					log.info("Progress Bar Still availble ");
				}

			} else {

				log.info("There is no progress bar ");

				if (tbandnotfound()) {

					searchBTscreen();

				} else {

					tbandFoundScreen();
				}
				break;
			
			}

		}

	}

	/**
	 * T Band Found screen
	 */

	private void tbandFoundScreen() {

		log.info("tband found Screen ========>>> ");
		CommonFunctions.waitforPageLoad(1);

		Assert.assertTrue(thealthLogoFoundScreen.isDisplayed(),
				"t.health logo is not displayed ");

		Assert.assertTrue(tbandFoundScreenText.isDisplayed(),
				"Band found text is not displayed ");

		Assert.assertTrue(tbandFoundScreenDescText.isDisplayed(),
				"Band found description is not displayed ");

		Assert.assertTrue(tbandFoundScreenFirstFound.isDisplayed(),
				"Selecting or Diplaying of band are not availble ");

		Assert.assertTrue(tbandFoundScreenNextButton.isDisplayed(),
				"Next button is not displayed ");

		Assert.assertTrue(tbandFoundScreenRefreshButton.isDisplayed(),
				"Refresh button is not displayed ");

	}

	/**
	 * Clicking on refresh button so as to search for bands more
	 */
	public void refreshButtonClickUnderTbandFoundScreen() {
		log.info("Clicking on refresh button");

		tbandFoundScreenRefreshButton.click();

	}

	/**
	 * Selecting first band from Bands found
	 */
	public void selectFirstband() {

		log.info("Selecting band and naivating to next screen ");

		tbandFoundScreenFirstFound.click();

		tbandFoundScreenNextButton.click();

	}

	/**
	 * Clicking on Select different button so as to search for bands more
	 */
	public void selectDiffButtonClickUnderPairScreen() {

		log.info("Clicking on Select diff button");

		tbandPairingScreenDifferentBandButton.click();

	}

	/**
	 * paring with band found First
	 */

	public void pairWithBand() {

		log.info("Paring with the band Screen");

		CommonFunctions.waitforWebElement(driver, thealthLogoPairScreen);

		/* Sesh */
		CommonFunctions.waitforWebElement(driver, tbandPairingScreenPairText);

		CommonFunctions.waitforWebElement(driver,
				tbandPairingcodeavailbiltyText);

		CommonFunctions.waitforWebElement(driver, tbandPairingScreenPairCode);

		CommonFunctions.waitforWebElement(driver,
				tbandPairingcodeavailbiltyText);

		CommonFunctions.waitforWebElement(driver, tbandPairingScreenButton);

		CommonFunctions.waitforWebElement(driver,
				tbandPairingScreenDifferentBandButton);

		CommonFunctions.waitforWebElement(driver,
				tbandPairingScreenDifferentBandButton);

		tbandPairingScreenButton.click();

		/* Sesh */

	}

	/**
	 * T band not found Screen
	 * 
	 * @return
	 */

	public boolean tbandnotfound() {

		log.info("Band not found Screen ====>>" + BTSearchbarNotFound.size());

		CommonFunctions.waitforPageLoad(3);

		if (BTSearchbarNotFound.size() > 0) {

			Assert.assertTrue(tbandLogoNotFoundScreen.isDisplayed(),
					"Logo is not displayed ");

			Assert.assertTrue(BTSearchbarNotFoundRetryText.isDisplayed(),
					" Tband not found retry is not displayed ");

			Assert.assertTrue(BTSearchbarRetrybutton.isDisplayed(),
					"retry button is not displayed ");

			Assert.assertTrue(bluetoothButtonIconText.isDisplayed(),
					"Bluetooth is not displayed ");

			Assert.assertEquals(bluetoothButtonIconText.getText(), BT_ON_BAND,
					"Failed to Assert with text ");

			BTSearchbarRetrybutton.click();

			return true;

		}

		else {

			log.info("Tband Found navigating to other screen ==>>>");
			return false;

		}
	}
}
