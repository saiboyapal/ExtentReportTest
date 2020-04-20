package com.crm.qa.tests;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest extends Testbase {
    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;

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

    @Test(priority = 1)
    public void validateHomePageTitleTest(){
        test = extent.createTest("validateHomePageTitleTest");
        String sTitle = homePage.homePageTitle();
        Assert.assertEquals( sTitle,"Cogmento CRM");
    }

    @Test(priority = 2)
    public void validateUsernameTest(){
        test = extent.createTest("validateUsernameTest");
        String sUserName = homePage.getUsername();
        Assert.assertEquals( sUserName,prop.getProperty("username"));
    }

    @Test(priority = 3)
    public void validateDealsLinkTest(){
        test = extent.createTest("validateDealsLinkTest");
        dealsPage = homePage.ckickDealsLink();
    }
}


