package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class AddCostemerTest extends TestBase {
	@Test(dataProviderClass = TestUtil.class,dataProvider="dp")
	//@Test(dataProvider="getData")
	public void addCostemerTest(Hashtable<String,String> data) {
		System.out.println(data.get("name") + data.get("lastname") + data.get("postcode")+ data.get("runmode"));
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		if(!data.get("runmode").equals("Y")) {
			throw new SkipException("skipping the test case as a run mode ");
		}
		click("acBtm_CSS");

		type("fnBtn_CSS", data.get("name"));

		type("lsBtn_CSS", data.get("lastname"));
		type("pcBtn_CSS", data.get("postcode"));
		click("okBtn_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
	}

//	@DataProvider
//public Object[][] getData() {
//		String sheetName = "AddCostemerTest";
//		int rows = excel.getRowCount(sheetName);
//		int cols = excel.getColumnCount(sheetName);
//
//		Object[][] data = new Object[rows - 2][cols];
//
//		// Hashtable<String,String> table = null;
//
//	for (int rowNum = 2; rowNum < rows; rowNum++) {
//
//		// table = new Hashtable<String,String>();
//
//			for (int colNum = 0; colNum < cols; colNum++) {
//
//			// data[rowNum-2][colNum]= excel.getCellData(sheetName, colNum, rowNum);
//
//				// excel.getCellData(sheetName, colNum, rowNum));
//				// data[rowNum-2][0]=table;
//				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//
//		}
//		}
//		return data;
//
//	}
}