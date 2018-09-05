package com.mobile.app.config;

import java.io.File;

public interface PagesHelper {

	final File qa_env_properties = new File(
			"./src/test/resources/com/mobile/env/QA.properties");

	final File snd_env_properties = new File(
			"./src/test/resources/com/v/mobile/SND.properties");

	// final String failureimagespath =
	// "http://10.11.15.181:8080/jobs/RegressionSuite/ExtentTestReportNewApp/target";

	final String failureimagespath = "/target";

	/** SETUP FLOW TEXT **/

	public static final String CONGRATS_MESAGE_SCREEN1 = "Congratulations on your new t·band.";
	public static final String AGREE_NOTICE = "t·band needs your phone’s bluetooth to synchronise data.";

	public static final String AGREE_NOTICE1 = "Allow bluetooth access?(We will turn it ON if required)";

	public static final String BT_SEARCH = "Searching for t·bands";

	public static final String BT_ON_BAND = "Press side button to turn on tband";

	public static final String PAIRING_DESCRIPTION_TEXT = "Make sure the code on the band matches with the one displayed above and then hit pair";

	/*** data to enter ****/

	public static final String ENTER_MOBILENUMBER = "";
	public static final String LOGIN_CONFIRMATION = "DashBoard";
	public static final String STOPWATCH_TITLE_CONFIRMATION = "StopWatch";

}
