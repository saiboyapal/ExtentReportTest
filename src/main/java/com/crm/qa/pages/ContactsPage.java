package com.crm.qa.pages;

import com.crm.qa.base.Testbase;
import com.crm.qa.utils.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class ContactsPage extends Testbase {

    //WebElements
    @FindBy(xpath="//div[contains(@class,'ui header')]")
    WebElement contactsHeader;

    @FindBy(xpath="//span[contains(text(),'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath="//button[contains(text(),'New')]")
    WebElement newBtn;

    @FindBy(xpath="//button[contains(text(),'Export')]")
    WebElement exportBtn;

    @FindBy(xpath="//button[contains(text(),'Show Filters')]")
    WebElement filtersBtn;

    @FindBy(xpath="//input[@name='first_name']")
    WebElement firstName;

    @FindBy(xpath="//input[@name='last_name']")
    WebElement lastName;

    @FindBy(xpath="//div[@name='company']//input[@class='search']")
    WebElement company;

    @FindBy(xpath="//i[@class='save icon']")
    WebElement saveBtn;

    @FindBy(xpath="//i[@class='cancel icon']")
    WebElement cancelBtn;

    @FindBy(xpath="//table[@class='ui celled sortable striped table custom-grid table-scroll']")
    WebElement contactsTable;


    //initialize the page elements
    public ContactsPage () {
        PageFactory.initElements(driver, this);
    }

    //Actions
    public String getHeader(){
        return contactsHeader.getText();
    }

    public boolean tableVisible(){
        return contactsTable.isDisplayed();
    }

    public void getTableData (){
        driver.navigate().refresh();
        List rowsList = contactsTable.findElements(By.tagName("tr"));
        System.out.println("There are rows " + rowsList.size());

        List columnsList=contactsTable.findElements(By.tagName("td"));
        System.out.println("Number of columns="+columnsList.size());

        /*for (WebElement row : rowsList) {
            System.out.println();
            columnsList = row.findElements(By.tagName("td"));

            for (WebElement column : columnsList) {
                System.out.print(column.getText() + ", ");
            }

        }*/

    }

    public void addContact(String fName, String lName, String cName){
        contactsLink.click();
        newBtn.click();
        driver.navigate().refresh();
        waituntil(driver,firstName, 20);
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        company.sendKeys(cName);
        saveBtn.click();
    }

}
