package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseHelper {

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//a[@href='#/user-profile']")
    WebElement login;
    public LoginPage clickIconAuthorization() {
        pause(2000);
        click(login);
        return this;
    }


    @FindBy(xpath = "//a[@href='#/login']")
    WebElement loginButton;
    public LoginPage clickLoginButton() {
        pause(2000);
        click(loginButton);
        return this;
    }


    @FindBy(xpath = "//input[@id='email-id']")
    WebElement userEmail;
    @FindBy(xpath = "//input[@id='password-id']")
    WebElement userPassword;
    public LoginPage enterPersonalData(String user, String password) {
        type(userEmail, user);
        type(userPassword, password);
        return this;
    }

    @FindBy(xpath = "//button[text()='Login']")
    WebElement submitLogin;
    public LoginPage clickSubmitLogin() {
        pause(2000);
        click(submitLogin);
        return this;
    }

    public String getErrorMessage(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds)); // Устанавливаем явное ожидание
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='alert']/div[2]"))); // Используем contains для большей гибкости
        return errorElement.getText(); // Возвращаем текст элемента
    }

    public void loginExistedUser() {
        new LoginPage(driver)
                .clickIconAuthorization()
                .clickLoginButton()
                .enterPersonalData("addproduct@gmail.com", "addproduct@gmail.com")
                .clickSubmitLogin();
    }


    @FindBy(xpath = "(//button[@type='button'])[2]")
    WebElement displayLogOUT;
    public LoginPage verifyUserIsLoggedIn() {
    assert displayLogOUT.isDisplayed();
        return this;
    }


    @FindBy(xpath = "//div[contains(text(), 'Login or email is invalid. Try again.')]")
    WebElement errorMessage;
    public LoginPage failAuthorLogin(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        assert errorMessage.getText().contains(text);
        return this;
    }

    @FindBy(xpath = "//button[normalize-space(text())='Log out']")
    WebElement logOutButton;
    public LoginPage logoutButton() {
        pause(2000);
        click(logOutButton);
        return this;
    }

    public void logout() {
        clickIconAuthorization();
        logoutButton();
    }
}
