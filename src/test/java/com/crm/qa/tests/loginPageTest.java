package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static com.crm.qa.utils.TestUtil.clearExtent;
import static com.crm.qa.utils.TestUtil.logTestResults;

public class loginPageTest extends Testbase {
    LoginPage loginPage;
    HomePage homePage;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public loginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp1(){
        System.out.println("this is before method");
        initialization();
        loginPage = new LoginPage();
    }

    @BeforeTest
    public void setUp(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        TestUtil.setExtent(extentReports);
        System.out.println("this is before test method");
    }

    @Test(priority = 1)
    public void validateLoginPageTitleTest() throws IOException {
        extentTest = extentReports.startTest("validateLoginPageTitleTest");
        String sTitle = loginPage.loginPageTitle();
        //String sFile = TestUtil.getScreenshotAtName("validateLoginPageTitleTest");
        //System.out.println("The screenshot is at " + sFile);
        Assert.assertEquals( sTitle,"Free CRM #1 cloud software for any business large or small");
    }

    @Test(priority = 2)
    public void validateCRMLogoTest(){
        extentTest = extentReports.startTest("validateCRMLogoTest");
        Assert.assertTrue(loginPage.crmLogDisplayed());
    }

    @Test(priority = 3)
    public void validateLoginTest(){
        extentTest = extentReports.startTest("validateLoginTest");
        homePage = loginPage.login(prop.getProperty("emailID"),prop.getProperty("password"));
        String sTitle = homePage.homePageTitle();
        Assert.assertEquals( sTitle,"Cogmento CRM");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException{
        logTestResults(extentTest, result);
        extentReports.endTest(extentTest); //ending test and ends the current test and prepare to create html report
        driver.quit();
        System.out.println("this is after method");
    }

    @AfterTest
    public void tearDown(){
        clearExtent(extentReports);
        System.out.println("this is after test method");
    }
}
