package com.crm.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.utils.TestUtil;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.crm.qa.utils.TestUtil.getScreenshotAtName;

public class Testbase {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties prop;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public Testbase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
                    + "/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void initReport () {
        // start reporters
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport.html");

        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterMethod
    public static void logTestResults(ITestResult result) throws IOException{
        if(result.getStatus()== ITestResult.FAILURE){
            //test.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
            test.fail(MarkupHelper.createLabel("Test case Failed is " + result.getName(), ExtentColor.RED));
            //extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
            test.fail(result.getThrowable());

            String screenshotPath = getScreenshotAtName(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            //extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
            //test.fail(result.);
        }
        else if(result.getStatus()==ITestResult.SKIP){
            //extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
            test.skip(MarkupHelper.createLabel("Test case Skipped is " + result.getName(), ExtentColor.YELLOW));
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            //extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
            test.pass(MarkupHelper.createLabel("Test case Passed is " + result.getName(), ExtentColor.GREEN));
        }

        driver.quit();
    }

    @AfterSuite
    public static void postResults() {
        extent.flush();
    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equals("FF")){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sainath\\IdeaProjects\\FreeCRMPOM\\src\\main\\resources\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        wait = new WebDriverWait(driver,20  );
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));

    }

    public static void waituntil(WebDriver driver, WebElement locator, int timeout){
        new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(locator));
    }

}
