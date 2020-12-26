package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {

	/*
	 * web driver properties extend class Dp Excell Mail
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream Fis;
	public static File f;
	public static Logger log = Logger.getLogger("devpiouLogger");
	public static ExcelReader excel = new ExcelReader(
			"C:\\Users\\ercan\\eclipse-workspace\\datadrivenogren\\src\\test\\resources\\excell\\TestData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeTest

	public WebDriver setUP() throws IOException {
		if (driver == null) {
			File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			FileInputStream fis = new FileInputStream(f);
			config.load(fis);

			log.debug(" Config File file loaded");

			f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			fis = new FileInputStream(f);

			OR.load(fis);
			
			
			if(System.getenv("browser")!=null && (!System.getenv("browser").isEmpty())){
				browser=System.getenv("browser");
				
			}
			
			else {
			browser = config.getProperty("browser");
			}
			// TODO Auto-generated method stub
			
			config.setProperty("browser",browser);
			
			if (browser.toString().equalsIgnoreCase("firefox")) {
				// System.setProperty("webdriver.gecko.driver",
				// "C:\\Users\\ercan\\Driver\\Gecodriver\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug(" firefox starting");
			}
			if (browser.toString().equalsIgnoreCase("ei")) {
				// log.debug("inter explorer is startting ");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				WebDriverManager.iedriver().setup();
				// System.setProperty("webdriver.ie.driver",
				// "C:\\Users\\ercan\\Driver\\IEDriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);

			}
			if (browser.toString().equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				// System.setProperty("webdriver.chrome.driver",
				// "C:\\Users\\ercan\\Driver\\Chromedriver\\chromedriver.exe");
				driver = new ChromeDriver();

			}
			if (browser.toString().equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				// System.setProperty("webdriver.ei.driver",
				// "C:\\Users\\ercan\\Driver\\Edge\\MicrosoftWebDriver.exe");
				driver = new EdgeDriver();

			}
			if (browser.toString().equalsIgnoreCase("opera")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				OperaOptions options = new OperaOptions();
				options.setBinary("C:\\Users\\ercan\\AppData\\Local\\Programs\\Opera\\72.0.3815.18\\Opera.exe");
				capabilities.setCapability(OperaOptions.CAPABILITY, options);
				WebDriverManager.operadriver().setup();
				// System.setProperty("webdriver.opera.driver",
				// "C:\\Users\\ercan\\Driver\\OperaDriver\\operadriver.exe");
				driver = new OperaDriver();

			}

		}

		driver.get(config.getProperty("TestURL"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")),
				TimeUnit.SECONDS);
		log.info("implicit wait choosen");
		wait = new WebDriverWait(driver, 5);
		log.info(" Explicit wait is set");
		driver.manage().window().maximize();
		log.info("page getting bigger");
		return driver;
	}

	public void click(String location) {
		if (location.endsWith("CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(location))).click();
		}

		else if (location.endsWith("XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(location))).click();
		} else if (location.endsWith("ID")) {
			driver.findElement(By.id(OR.getProperty(location))).click();
		}
		test.log(LogStatus.INFO, "Clicking on:  " + location);

	}

	public void type(String location, String value) {
		if (location.endsWith("CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(location))).sendKeys(value);
		} else if (location.endsWith("XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(location))).sendKeys(value);
		} else if (location.endsWith("ID")) {
			driver.findElement(By.id(OR.getProperty(location))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "typing in this :  " + location + " enter value as the " + value);

	}

	public static boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}

	}

	
static WebElement dropdown;
	public static void select(String lacotion, String value) {
		if (lacotion.endsWith("CSS")) {
			dropdown= driver.findElement(By.cssSelector(OR.getProperty(lacotion)));
		} else if (lacotion.endsWith("XPATH")) {
			dropdown=	driver.findElement(By.xpath(OR.getProperty(lacotion)));
		} else if (lacotion.endsWith("ID")) {
			dropdown=	driver.findElement(By.id(OR.getProperty(lacotion)));
		}
		Select select= new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "selecting  dropdoen  :  " + lacotion + " select value as the " + value);

	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			AssertJUnit.assertEquals(expected, actual);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			Reporter.log(" <br>" + " vericication failure :" + t.getMessage() + "<br>");
			// reportNG
			Reporter.log("<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// ExtentReport
			test.log(LogStatus.FAIL, " validation failed with exeptin : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}

	@AfterMethod
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}
}