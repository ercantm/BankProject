package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	// @Test(dataProviderClass = TestUtil.class,dataProvider="dp")
	@Test(dataProvider = "getData")
	public void openAccountTest(String costemer, String currency, String alertText) {
		// ==================clasmiyor ============================//
		// ==================clasmiyor ============================//
		// ==================clasmiyor ============================//
		// ==================clasmiyor ============================//

//		if (!(TestUtil.isTestRunnable("OpenAccountTest", excel))) {
//
//			throw new SkipException("Skipping the test " + "openAccountTest".toUpperCase() + "as the Run mode is NO");
//		}
		
		System.out.println(costemer + currency + alertText);
		// driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		click("opena_CSS");

		select("cos_CSS", costemer);
		select("cur_CSS", currency);
		click("pr_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "OpenAccountTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 2][cols];

		// Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum < rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {
//
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//
				// table.put(excel.getCellData(sheetName, colNum, 1),
// excel.getCellData(sheetName, colNum, rowNum));
				// data[rowNum-2][0]=table;
//
//data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//
			}
		}
		return data;
//
	}
}