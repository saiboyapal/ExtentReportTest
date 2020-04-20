package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactsPageTest extends Testbase{
    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;
    ContactsPage contactsPage;

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

    @Test(priority = 1)
    public void validateHeaderTest(){
        //extentTest = extentReports.startTest("validateHeaderTest");
        test = extent.createTest("validateHeaderTest");
        String sHeader = contactsPage.getHeader();
        Assert.assertEquals( sHeader,"Contacts");
    }

    @Test(priority = 3)
    public void validateContactsTable(){
        test = extent.createTest("validateContactsTable");
        contactsPage.getTableData();
        Assert.assertTrue(contactsPage.tableVisible());
    }
}
