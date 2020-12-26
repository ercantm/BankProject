package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;

import org.openqa.selenium.By;

import base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException {
		verifyEquals(config.getProperty("TestURL"), "http://www.way2automation.com/angularjs-protractor/banking/#/login" );
		test.log(LogStatus.INFO, "testing test url:  "+config.getProperty("TestURL"));
		log.info("testing test url:  "+config.getProperty("TestURL"));
		// asadaki kodu yazdik ki index. htm reporta fotagraflar ciksin
		// bu aldaki alip liste clasina ekleriz ki htm ciksin
//System.setProperty("org.uncommons.reportng.escape-output","false");
		log.debug(" bank manager login testing start ");
		click(("bmlBtn_CSS"));
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("acBtm_CSS"))),
				" bank manager loggin is not succesfull");
		log.debug(" bank manager login  tested and work perfect ");

		// Report.log yazdiklarimiz direk raporta gozukur
		// be sekilde her yapilan teste bunu yazmamiz gerekiyor. bunun icin listener
		// ekleriz?
//		 
//		 Reporter.log("ercan ekledi  bank manager login  tested and work perfect ");
//		 // target +blank new tap acar resmi 
//		 Reporter.log("<a target= \"_blank\" href=\"C:\\Users\\ercan\\OneDrive\\Desktop\\ercan.jpg height=200 width=200\"> screan shot</a>");
		boolean f = isElementPresent(By.cssSelector(OR.getProperty("acBtm_CSS")));
		System.out.println(f);
		Assert.assertTrue(false);
	}
}
