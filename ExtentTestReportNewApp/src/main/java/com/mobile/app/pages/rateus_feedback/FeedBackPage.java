package com.mobile.app.pages.rateus_feedback;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class FeedBackPage extends Base {
	static Logger log = Log4jUtil.loadLogger(FeedBackPage.class);

	public FeedBackPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
}
