package com.mobile.app.email;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.mobile.api.Log4jUtil;

/**
 * 
 * @author MChavali
 *
 */
public class SendEmail {

	static Logger log = Log4jUtil.loadLogger(SendEmail.class);

	static StringBuilder sb = new StringBuilder();

	final static String filePath = System.getProperty("user.dir")
			+ "/src/test/resources/com/mobile/email.config/Email.properties";

	final static String path = System.getProperty("user.dir")
			+ "\\target\\surefire-reports\\MobileExtentResults.html";

	/**
	 * HTML template in email to send
	 * 
	 * @param numberOfPassedTests
	 * @param numberOfFailedTests
	 * @param numberOfSkippedTests
	 * @return
	 */

	public static String genrateHTMLEmail(int numberOfPassedTests,
			int numberOfFailedTests, int numberOfSkippedTests) {

		sb.append("<!DOCTYPE html>");

		sb.append("<html> ");

		sb.append("<head> ");

		sb.append("<meta charset='UTF-8'> ");

		sb.append("<title>Automation Execution Results Summary</title>");

		sb.append("<style type='text/css'>");

		sb.append("body, html  { height: 100%; }");

		sb.append("h1 {" + "    color: blue;" + "    font-family: verdana;"
				+ "    font-size: 160%;" + "}" + "p  {" + "    color: red;"
				+ "    font-family: courier;" + "   font-size: 160%;" + "}");

		sb.append("html, body, div, span, applet, object, iframe,"
				+ " p, blockquote, pre,"
				+ "	a, abbr, acronym, address, big, cite, code,"
				+ "del, dfn, em, font, img, ins, kbd, q, s, samp,"
				+ "	small, strike, strong, sub, sup, tt, var,"
				+ "			b, u, i, center,"
				+ "			dl, dt, dd, ol, ul, li,"
				+ "			fieldset, form, label, legend,"
				+ "			table, caption, tbody, tfoot, thead, tr, th, td {"
				+ "				margin: 0;"
				+ "				padding: 0;"
				+ "				border: 0;"
				+ "				outline: 0;"
				+ "				font-size: 100%;"
				+ "				vertical-align: baseline;"
				+ "				background: transparent;"
				+ "			}"
				+ "			body { line-height: 1; }"
				+ "			ol, ul { list-style: none; }"
				+ "			blockquote, q { quotes: none; }"
				+ "			blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }"
				+ "			:focus { outline: 0; }"
				+ "			del { text-decoration: line-through; }"
				+ "			table {border-spacing: 0; } "
				+ "			body{"
				+ "				font-family:Arial, Helvetica, sans-serif;"
				+ "				background: url(background.jpg);"
				+ "				margin:0 auto;"
				+ "				width:520px;"
				+ "			}"
				+ "			a:link {"
				+ "				color: #666;"
				+ "				font-weight: bold;"
				+ "				text-decoration:none;"
				+ "			}"
				+ "			a:visited {"
				+ "				color: #666;"
				+ "				font-weight:bold;"
				+ "				text-decoration:none;"
				+ "			}"
				+ "			a:active,"
				+ "			a:hover {"
				+ "				color: #bd5a35;"
				+ "				text-decoration:underline;"
				+ "			}"
				+ "table a:link {"
				+ "				color: #666;"
				+ "				font-weight: bold;"
				+ "				text-decoration:none;"
				+ "			}"
				+ "			table a:visited {"
				+ "				color: #999999;"
				+ "				font-weight:bold;"
				+ "				text-decoration:none;"
				+ "			}"
				+ "			table a:active,"
				+ "			table a:hover {"
				+ "				color: #bd5a35;"
				+ "				text-decoration:underline;"
				+ "			}"
				+ "			table {"
				+ "				font-family:Arial, Helvetica, sans-serif;"
				+ "				color:#666;"
				+ "				font-size:12px;"
				+ "				text-shadow: 1px 1px 0px #fff;"
				+ "				background:#eaebec;"
				+ "				margin:20px;"
				+ "				border:#ccc 1px solid;"
				+ "				-moz-border-radius:3px;"
				+ "				-webkit-border-radius:3px;"
				+ "				border-radius:3px;"
				+ "				-moz-box-shadow: 0 1px 2px #d1d1d1;"
				+ "				-webkit-box-shadow: 0 1px 2px #d1d1d1;"
				+ "				box-shadow: 0 1px 2px #d1d1d1;"
				+ "			}"
				+ "		table th {"
				+ "				padding:21px 25px 22px 25px;"
				+ "				border-top:1px solid #fafafa;"
				+ "				border-bottom:1px solid #e0e0e0;"
				+ "				background: #494949;"
				+ "				background: -webkit-gradient(linear, left top, left bottom, from(#494949), to(#1c1c1c));"
				+ "				background: -moz-linear-gradient(top,  #494949,  #494949);"
				+ "			}"
				+ "			table th:first-child{"
				+ "				text-align: left;"
				+ "				padding-left:20px;"
				+ "			}"
				+ "			table tr:first-child th:first-child{"
				+ "				-moz-border-radius-topleft:3px;"
				+ "				-webkit-border-top-left-radius:3px;"
				+ "				border-top-left-radius:3px;"
				+ "			}"
				+ "			table tr:first-child th:last-child{"
				+ "				-moz-border-radius-topright:3px;"
				+ "				-webkit-border-top-right-radius:3px;"
				+ "				border-top-right-radius:3px;"
				+ "			}"
				+ "			table tr{"
				+ "				text-align: center;"
				+ "				padding-left:20px;"
				+ "			}"
				+ "			table tr td:first-child{"
				+ "				text-align: left;"
				+ "				padding-left:20px;"
				+ "				border-left: 0;"
				+ "			}"
				+ "			table tr td {"
				+ "				padding:18px;"
				+ "				border-top: 1px solid #ffffff;"
				+ "				border-bottom:1px solid #e0e0e0;"
				+ "				border-left: 1px solid #e0e0e0;"
				+ "								background: #8a8a8a;"
				+ "				background: -webkit-gradient(linear, left top, left bottom, from(#8a8a8a), to(#8b8b8b));"
				+ "			background: -moz-linear-gradient(top,  #8b8b8b,  #8a8a8a);"
				+ "		}"
				+ "			table tr.even td{"
				+ "				background: #f6f6f6;"
				+ "				background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));"
				+ "			background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);"
				+ "			}"
				+ "			table tr.MainHeader th{"
				+ "				background: #f6f6f6;"
				+ "				background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));"
				+ "				background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);"
				+ "				color:#666;"
				+ "				width:100%;"
				+ "		}"
				+ "			table tr:last-child td{"
				+ "				border-bottom:0;"
				+ "			}"
				+ "			table tr:last-child td:first-child{"
				+ "				-moz-border-radius-bottomleft:3px;"
				+ "				-webkit-border-bottom-left-radius:3px;"
				+ "	border-bottom-left-radius:3px;"
				+ "			}"
				+ "			table tr:last-child td:last-child{"
				+ "				-moz-border-radius-bottomright:3px;"
				+ "				-webkit-border-bottom-right-radius:3px;"
				+ "				border-bottom-right-radius:3px;"
				+ "			}"
				+ "			table tr:hover td{"
				+ "				background: #f2f2f2;"
				+ "				background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));"
				+ "				background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);"
				+ "}");

		sb.append("</style>");

		sb.append("</head>");

		sb.append("<body>");

		sb.append("<header>"
				+ "<p style='color: #46291F; font-family:Arial Bold; font-size:1.4em;'> Hi Team, </p>"
				+ "<p>      </p>"
				+ "<br>"
				+ "<p style='color: #46291F; font-family:Arial Bold; font-size:1.4em;'> Please find below information on Tests Execution"
				+ "</p>" + "</header>");

		sb.append("<table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->");

		sb.append("<tr><th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Module/Suite</th>"
				+ "<th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Total Testcases</th>"
				+ "<th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Passed Tests</th>"
				+ "<th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Failed Tests</th>"
				+ "<th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Skipped Tests</th>"
				+ "<th style='color: #1496BB; font-family:Copperplate Gothic Bold; font-size:1.4em;'>Pass Percentage</th>"
				+ "</tr><!-- Table Header -->");

		int TotalTC = numberOfPassedTests + numberOfFailedTests
				+ numberOfSkippedTests;
		double percentage = 0;

		try {

			percentage = (double) Math.round((numberOfPassedTests * 100)
					/ TotalTC);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String moduleName = System.getProperty("groupsName");
		if (moduleName == null) {

			moduleName = System.getProperty("suiteName");

		}
		sb.append("<tr><td style='color: #161617; font-family:Arial Bold; font-size:1.4em;'>"
				+ moduleName
				+ "</td>"
				+ "<td style='color: #161617; font-family:Arial Bold; font-size:1.4em;'>"
				+ TotalTC
				+ "</td>"
				+ "<td style='color: #33FF3C; font-family:Arial Bold; font-size:1.4em;'>"
				+ numberOfPassedTests
				+ "</td>"
				+ "<td style='color: #FA0707; font-family:Arial Bold; font-size:1.4em;'>"
				+ numberOfFailedTests
				+ "</td>"
				+ "<td style='color: #D3F70B; font-family:Arial Bold; font-size:1.4em;'>"
				+ numberOfSkippedTests
				+ "</td>"
				+ "<td style='color: #161617; font-family:Arial Bold; font-size:1.4em;'>"
				+ percentage + "</td>" + "</tr><!-- Table Row -->");

		/*
		 * sb.append(
		 * "<tr class='even'><td>Take the dog for a walk</td><td>100%</td><td>Yes</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr><!-- Darker Table Row -->"
		 * );
		 * 
		 * sb.append(
		 * "<tr><td>Waste half the day on Twitter</td><td>20%</td><td>No</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 * 
		 * sb.append(
		 * "<tr class='even'><td>Feel inferior after viewing Dribble</td><td>80%</td><td>No</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 * 
		 * sb.append(
		 * "<tr><td>Wince at 'to do' list</td><td>100%</td><td>Yes</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 * 
		 * sb.append(
		 * "<tr class='even'><td>Vow to complete personal project</td><td>23%</td><td>yes</td><td>Take the dog for a walk</td><td>100%</td><td><a href=inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 * 
		 * sb.append(
		 * "<tr><td>Procrastinate</td><td>80%</td><td>No</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 * 
		 * sb.append(
		 * "<tr class='even'><td>Procrastinate</td><td>80%</td><td>Yes</td><td>Take the dog for a walk</td><td>100%</td><td><a href='#inexistent-id'>moreInfo</a></td></tr>"
		 * );
		 */
		sb.append("</table>");

		/*
		 * sb.append(
		 * "	<center><h2><a  href=http://www.google.com'>Google</a></h2></center>"
		 * );
		 * 
		 * sb.append(
		 * "<center><h2><a href='file:///home/samrtron/sandeep.txt'>myfile</a></h2></center>"
		 * );
		 */

		sb.append("<header>"
				+ "<br>"
				+ "<p style='color: #5F382A;font-family:Copperplate Gothic Bold; font-size:1.4em;'> Please find the attached sheet for More information on Tests Execution results"
				+ "</p>" + "</header>");

		sb.append("</body>");

		sb.append("</html>");

		String html = sb.toString();

		return html;

	}

	/**
	 * This method is to send the email configured under properties file
	 * 
	 * @param numberOfPassedTests
	 * @param numberOfFailedTests
	 * @param numberOfSkippedTests
	 */
	public static void mailGenarate(int numberOfPassedTests,
			int numberOfFailedTests, int numberOfSkippedTests) {

		try {
			FileInputStream fileInput = new FileInputStream(filePath);

			Properties prop = new Properties();

			log.info("Path of email connfiguration==>>  " + filePath);

			prop.load(fileInput);

			String toRecipent = prop.getProperty("to");

			String[] recipents = toRecipent.split(",");

			log.info("Email sending to the Recipients ==>>  " + toRecipent);

			int portNumer = Integer.parseInt(prop.getProperty("SMTPport"));

			HtmlEmail email = new HtmlEmail();

			email.setHostName(prop.getProperty("hostName"));

			email.setSmtpPort(portNumer);

			email.setAuthenticator(new DefaultAuthenticator(prop
					.getProperty("username"), prop.getProperty("password")));

			email.setSSLOnConnect(true);

			email.setFrom(prop.getProperty("From"), "Automation Team");

			email.setSubject(prop.getProperty("subject"));

			email.addTo(recipents);

			email.attach(new File(path));

			// set the html message
			email.setHtmlMsg(genrateHTMLEmail(numberOfPassedTests,
					numberOfFailedTests, numberOfSkippedTests));

			boolean value = Boolean.parseBoolean(prop
					.getProperty("ConfigureEmail"));

			if (value) {

				log.info("Sending email to the recipients....");

				email.send();

				log.info("Email sent to all the recipients listed ==>>");

			} else {

				log.info("There is NO E-mail Sending Configuration...");
			}
		} catch (EmailException ex) {

			log.error(ex.getMessage());

		} catch (Exception e) {

			log.error(e.getMessage());
		}

	}

}
