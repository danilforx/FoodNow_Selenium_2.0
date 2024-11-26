package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends BaseHelper {
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "(//input[@class='css-1l4kvas'])[1]")
    WebElement address;
    public OrderDetailsPage enterAddress() {
        type(address, "123 Main Street");
        return this;
    }


    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitOrder;
    public OrderDetailsPage clickSubmitOrder() {
        pause(2000);
        click(submitOrder);
        return this;
    }
}
