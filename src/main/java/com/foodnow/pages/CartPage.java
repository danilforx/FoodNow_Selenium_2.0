package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BaseHelper {
    public CartPage(WebDriver driver) {
        super(driver);
    }


    @FindBy (xpath = "(//img[@class='css-13s0wde'])[2]")
    WebElement cartIcon;
    public CartPage clickIconCart() {
        pause(2000);
        click(cartIcon);
        return this;
    }


    @FindBy(xpath = "(//button[contains(@class,'MuiButton-root')])[2]")
    WebElement plusButton;
    public CartPage clickPlusButton() {
        pause(2000);
        click(plusButton);
        return this;
    }

    @FindBy(xpath = "(//div[@class='css-1361dhl']//div)[2]")
    WebElement checkout;
    public CartPage proceedToCheckout() {
        pause(2000);
        click(checkout);
        return this;
    }

    public void addProductToCart() {
        new AddProductPage(driver)
                .clickFoodCategory()
                .clickMeatCategory()
                .clickAddSteakToCart();
    }

    public void checkoutToCart() {
        new CartPage(driver)
                .clickIconCart()
                .clickPlusButton()
                .proceedToCheckout();
    }
}
