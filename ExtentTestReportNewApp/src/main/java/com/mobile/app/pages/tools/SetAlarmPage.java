package com.mobile.app.pages.tools;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.mobile.api.Log4jUtil;
import com.mobile.app.config.Base;

public class SetAlarmPage extends Base {
	static Logger log = Log4jUtil.loadLogger(SetAlarmPage.class);

	public SetAlarmPage() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
}
