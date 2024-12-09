package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BaseHelper {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='#/user-profile']")
    WebElement login;
    public RegisterPage clickIconAuthorization() {
        click(login);
        return this;
    }

    @FindBy(xpath = "//a[@href='#/registration' and text()='or register']")
    WebElement registerButton; // private
    public RegisterPage clickRegistrButton() {
        click(registerButton);
        return this;
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

    public RegisterPage enterPersonalData(String firstName, String lastName, String mail, String password, String phone) {
        type(userfirstName, firstName);
        type(userlastName, lastName);
        type(userEmail, mail);
        type(userPassword, password);
        type(userPhone, phone);
        return this;
    }

    @FindBy(xpath = "//button[text()='Registration']")
    WebElement submitRegister;
    public RegisterPage clickSubmitRegister() {
        click(submitRegister);
        return this;
    }

    @FindBy(xpath = "//div[@role='alert']")
    WebElement popUp;
    public void verifyUserIsLoggedIn() {
        // pause(5000); // Adding a pause of 5 seconds
       // shouldHaveText(popUp, "You were registered successfully. Please log in now.",2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(popUp));
        assert popUp.isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//div[@role='alert']/div[2]")).getText();
    }


    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'First name')]")
    WebElement invalidFirstName;
    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'Last name')]")
    WebElement invalidLastName;
    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'Email')]")
    WebElement invalidEmail;
    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'Password')]")
    WebElement invalidPassword;
    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'Phone number')]")
    WebElement invalidPhone;
    public RegisterPage failAuthorizationLogin(String text, String text1, String text2,String text3, String text4) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(invalidFirstName));
        wait.until(ExpectedConditions.visibilityOf(invalidLastName));
        wait.until(ExpectedConditions.visibilityOf(invalidEmail));
        wait.until(ExpectedConditions.visibilityOf(invalidPassword));
        wait.until(ExpectedConditions.visibilityOf(invalidPhone));
        assert invalidFirstName.getText().contains(text);
        assert invalidLastName.getText().contains(text1);
        assert invalidEmail.getText().contains(text2);
        assert invalidPassword.getText().contains(text3);
        assert invalidPhone.getText().contains(text4);
        return this;
    }
}
