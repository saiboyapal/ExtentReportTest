package com.crm.qa.pages;

import com.crm.qa.base.Testbase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Testbase {

    //WebElements
    @FindBy(xpath="//span[@class='user-display']")
    WebElement usernameLabel;

    @FindBy(xpath="//span[text()='Calendar']")
    WebElement calendarLink;

    @FindBy(xpath="//span[contains(text(),'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath="//span[contains(text(),'Companies')]")
    WebElement companiesLink;

    @FindBy(xpath="//span[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath="//span[contains(text(),'Tasks')]")
    WebElement tasksLink;


    //initialize the page elements
    public HomePage () {
        PageFactory.initElements(driver, this);
    }

    public String homePageTitle() {
        return driver.getTitle();
    }

    public String getUsername() {
        return usernameLabel.getText();
    }

    public DealsPage ckickDealsLink() {
        dealsLink.click();
        return new DealsPage();
    }

    public ContactsPage cickContactsLink() {
        contactsLink.click();
        return new ContactsPage();
    }
}
