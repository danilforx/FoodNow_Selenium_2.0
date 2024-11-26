package com.foodnow.pages;

import com.foodnow.core.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddProductPage extends BaseHelper {

    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class, 'MuiButtonBase-root') and text()='Meat']")
    WebElement meatCategory;
    public AddProductPage clickMeatCategory() {
        pause(2000);
        click(meatCategory);
        return this;

    }

    @FindBy(xpath = "(//img[@class='css-rbpvgo'])[1]")
    WebElement mainProductPage;
    public AddProductPage clickFoodCategory() {
        pause(2000);
        click(mainProductPage);
        return this;
    }

    @FindBy(xpath = "(//div[@class='MuiStack-root css-jj2ztu'])[2]")
    WebElement addSteakToCart;
    public AddProductPage clickAddSteakToCart() {
        pause(2000);
        click(addSteakToCart);
        return this;
    }


    @FindBy(xpath = "//div[contains(text(), 'Rump Steak was added to cart')]")
    WebElement steakAddedToCart;
    public void verifyProductIsAdded() {
        shouldHaveText(steakAddedToCart, "Rump Steak was added to cart", 2000);
    }




    public void addProduct() {
        new AddProductPage(driver)
                .clickFoodCategory()
                .clickMeatCategory()
                .clickAddSteakToCart()
                .verifyProductIsAdded();
    }
}
