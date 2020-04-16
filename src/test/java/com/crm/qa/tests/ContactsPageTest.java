package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.ContactsPage;
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

public class ContactsPageTest extends Testbase{
    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;
    ContactsPage contactsPage;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public ContactsPageTest() {
            super();
        }

    @BeforeMethod
    public void setUp1(){
        System.out.println("this is before method");
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("emailID"),prop.getProperty("password"));
        contactsPage = homePage.cickContactsLink();
    }

    @BeforeTest
    public void setUp(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        TestUtil.setExtent(extentReports);
        System.out.println("this is before test method");
    }

    @Test(priority = 1)
    public void validateHeaderTest(){
        extentTest = extentReports.startTest("validateHeaderTest");
        String sHeader = contactsPage.getHeader();
        Assert.assertEquals( sHeader,"Contacts");
    }

   /* @Test(priority = 2, dataProvider="getCRMTestData")
    public void validateAddContacts(String fName, String lName, String cName){
        contactsPage.addContact( fName, lName, cName);
    }*/


    @Test(priority = 3)
    public void validateContactsTable(){
        extentTest = extentReports.startTest("validateContactsTable");
        contactsPage.getTableData();
        Assert.assertTrue(contactsPage.tableVisible());
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
