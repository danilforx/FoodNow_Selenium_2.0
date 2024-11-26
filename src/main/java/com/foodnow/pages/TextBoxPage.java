package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextBoxPage extends BaseHelper {
        public TextBoxPage(WebDriver driver) {
            super(driver);
        }

    @FindBy(xpath = "//input[@id='firstName-id']")
    WebElement userfirstName;
    @FindBy(xpath = "//input[@id='lastName-id']")
    WebElement userlastName;
    @FindBy(xpath = "//input[@id='email-id']")
    WebElement userEmail;
    @FindBy(xpath = "//input[@id='password-id']")
    WebElement userPassword;
    @FindBy(xpath = "//input[@id='phoneNumber-id']")
    WebElement userPhone;

        public TextBoxPage enterPersonalData(String firstName, String lastName, String email, String password, String phone) {
            type(userfirstName, firstName);
            type(userlastName, lastName);
            type(userEmail, email);
            type(userPassword, password);
            type(userPhone, phone);
            return this;
        }

}
