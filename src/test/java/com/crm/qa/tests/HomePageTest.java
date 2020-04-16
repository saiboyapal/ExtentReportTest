package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.DealsPage;
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

public class HomePageTest extends Testbase {
    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public HomePageTest() {
        super();
    }


    @BeforeMethod
    public void setUp1(){
        System.out.println("this is before method");
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("emailID"),prop.getProperty("password"));
    }

    @BeforeTest
    public void setUp(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        TestUtil.setExtent(extentReports);
        System.out.println("this is before test method");
    }

    @Test(priority = 1)
    public void validateHomePageTitleTest(){
        extentTest = extentReports.startTest("validateHomePageTitleTest");
        String sTitle = homePage.homePageTitle();
        Assert.assertEquals( sTitle,"Cogmento CRM");
    }

    @Test(priority = 2)
    public void validateUsernameTest(){
        extentTest = extentReports.startTest("validateUsernameTest");
        String sUserName = homePage.getUsername();
        Assert.assertEquals( sUserName,prop.getProperty("username"));
    }

    @Test(priority = 2)
    public void validateDealsLinkTest(){
        extentTest = extentReports.startTest("validateDealsLinkTest");
        dealsPage = homePage.ckickDealsLink();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
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


