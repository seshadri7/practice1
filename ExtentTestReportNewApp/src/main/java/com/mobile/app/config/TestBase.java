package com.mobile.app.config;

import org.apache.log4j.Logger;

import com.mobile.api.Log4jUtil;
import com.mobile.app.pages.DashboardPage;
import com.mobile.app.pages.ForgotPasswordPage;
import com.mobile.app.pages.LoginPage;
import com.mobile.app.pages.goalsplan.CustomizePlanSettings;
import com.mobile.app.pages.goalsplan.Goalspage;
import com.mobile.app.pages.rateus_feedback.FeedBackPage;
import com.mobile.app.pages.rateus_feedback.RateUSPage;
import com.mobile.app.pages.tools.NotificationsPage;
import com.mobile.app.pages.tools.SetAlarmPage;
import com.mobile.app.pages.tools.SetTimerPage;
import com.mobile.app.pages.tools.StopWatchPage;

/**
 * 
 * @author mchavali
 *
 */
public class TestBase extends Base {

	static Logger log = Log4jUtil.loadLogger(TestBase.class);

	public static DashboardPage dashBoardpage;

	public static ForgotPasswordPage forgotPasswordPage;

	public static LoginPage loginPage;

	public static Goalspage goalPage;

	public static CustomizePlanSettings customizePlanSettings;

	public static NotificationsPage notificationsPage;

	public static SetAlarmPage setAlarmPage;

	public static SetTimerPage setTimerPage;

	public static StopWatchPage stopWatchPage;

	public static FeedBackPage FeedBackPage;

	public static RateUSPage rateUSPage;

	public static void killBrowserSession() {

		if (Base.getPlatformName().equalsIgnoreCase("Android")) {

			log.info("============================================");

			driver.quit();

			log.info("Closing Android Session");

		}

		else {

			log.info("============================================");

			log.info("No browser Selected ");

		}
	}

}
