package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class loginPageTest extends Testbase {
    LoginPage loginPage;
    HomePage homePage;

    public loginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("this is before method");
        initialization();
        loginPage = new LoginPage();
    }

    /*@BeforeSuite
    public void setUp(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        TestUtil.setExtent(extentReports);
        System.out.println("this is before Suite  method");
    }*/

    @Test(priority = 1)
    public void validateLoginPageTitleTest() throws IOException {
        test = extent.createTest("validateLoginPageTitleTest");
        String sTitle = loginPage.loginPageTitle();
        //String sFile = TestUtil.getScreenshotAtName("validateLoginPageTitleTest");
        //System.out.println("The screenshot is at " + sFile);
        Assert.assertEquals( sTitle,"Free CRM #1 cloud software for any business big or small");
    }

    @Test(priority = 2)
    public void validateCRMLogoTest(){
        test = extent.createTest("validateCRMLogoTest");
        Assert.assertTrue(loginPage.crmLogDisplayed());
    }

    @Test(priority = 3)
    public void validateLoginTest(){
        test = extent.createTest("validateLoginTest");
        homePage = loginPage.login(prop.getProperty("emailID"),prop.getProperty("password"));
        String sTitle = homePage.homePageTitle();
        Assert.assertEquals( sTitle,"Cogmento CRM");
    }

    /*@AfterMethod
    public void tearDown(ITestResult result) throws IOException{
        logTestResults(extentTest, result);
        extentReports.endTest(extentTest); //ending test and ends the current test and prepare to create html report
        driver.quit();
        System.out.println("this is after method");
    }*/

    /*@AfterSuite
    public void tearDown(){
        clearExtent(extentReports);
        System.out.println("this is after suite method");
    }*/
}
