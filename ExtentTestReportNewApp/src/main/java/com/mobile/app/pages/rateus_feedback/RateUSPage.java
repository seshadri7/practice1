package com.mobile.app.pages.rateus_feedback;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class RateUSPage extends Base {
	static Logger log = Log4jUtil.loadLogger(RateUSPage.class);
	
	public RateUSPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
}
