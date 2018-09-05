package com.mobile.app.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mobile.api.Log4jUtil;

public class Base implements PagesHelper {

	static Logger log = Log4jUtil.loadLogger(Base.class);
	@SuppressWarnings("rawtypes")
	public static AppiumDriver driver;

	private static String AppiumServerURL = "";

	private static String TestDeviceName = "";

	private static String appPackageName = "";

	private static String appActivityName = "";

	private static String platformName = "";

	private static String platformVersion = "";

	private static String username = "";

	private static String password = "";

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	private static InputStream inputStream;

	private static Properties prop = new Properties();

	static {

		try {

			if (System.getProperty("env.USER").equalsIgnoreCase("qa")) {

				inputStream = new FileInputStream(qa_env_properties);

				prop.load(inputStream);

				System.out.println("Android Environment Version is selected ");

			} else {

				System.out.println("No Environment is selected ");
			}

			// get the property value and print it out
			AppiumServerURL = prop.getProperty("AppiumServerURL");

			TestDeviceName = prop.getProperty("TestDeviceName"); 

			appPackageName = prop.getProperty("appPackageName");

			appActivityName = prop.getProperty("appActivityName");

			platformName = prop.getProperty("platformName");

			platformVersion = prop.getProperty("platformVersion");

			username = prop.getProperty("tUserName");

			password = prop.getProperty("tPassword");

		} catch (Exception e) {

			log.info("Exception: " + e);

		} finally {
			try {

				inputStream.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public static void intailizeApp() throws MalformedURLException

	{
		try {
			if (platformName.equalsIgnoreCase("Android")) {

				log.info("Andriod NativeApp browser selected ");

				DesiredCapabilities cap = new DesiredCapabilities();

				cap.setCapability(CapabilityType.BROWSER_NAME, "");

				cap.setCapability("platformName", platformName);

				cap.setCapability("platformVersion", platformVersion);

				cap.setCapability("deviceName", TestDeviceName);

				cap.setCapability("appPackage", appPackageName);

				cap.setCapability("appActivity", appActivityName);
						
				driver = new AndroidDriver(new URL(AppiumServerURL), cap);

				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			}

		} catch (WebDriverException e) {

			e.printStackTrace();
		}

	}

	/**
	 * @return the platformName
	 */
	public static String getPlatformName() {
		return platformName;
	}

}
