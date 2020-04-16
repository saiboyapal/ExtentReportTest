package com.crm.qa.pages;

import com.crm.qa.base.Testbase;
import org.openqa.selenium.support.PageFactory;

public class DealsPage extends Testbase {

    //initialize the page elements
    public DealsPage () {
        PageFactory.initElements(driver, this);
    }
}
