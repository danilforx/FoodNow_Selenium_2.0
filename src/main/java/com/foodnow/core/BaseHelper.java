package com.foodnow.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger; //нужно выбирать импорт библиотеки org.slf4j.Logger
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseHelper {
    Logger logger = LoggerFactory.getLogger(BaseHelper.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;


    public BaseHelper(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);

    }

    protected void click(WebElement element) {
        element.click();
        logger.info("[" + element + "] is pressed");
    }

    protected void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(isTextPresent,
                    "Expected text: [" + text + "], actual text: [" + element.getText() + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            //throw new AssertionError("Text '" + text + "' not found in element after " + timeout + "ms.  Actual text: " + element.getText(), e);
        }
    }

    protected boolean isElementPresent(By locator) {
        // logger.info("Проверка есть ли элемент [" + locator + "] на странице");
        return driver.findElements(locator).size() > 0;
    }


    protected boolean isAlertPresent() { // Проверка наличия алерта
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        try {
            logger.warn("Alert has text: [" + alert.getText() + "]");
            alert.accept();
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    protected static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Adding a pause of 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void scrollTo(int y) {
        js.executeScript("window.scrollBy(" + 0 + "," + y + ")");
    }

    public String takeScreenshot() {
        File screenshot = new File("src/test_screenshots/screen-"+ System.currentTimeMillis() + ".png");
        try {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(tmp.toPath(), screenshot.toPath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        //logger.info("Screenshot saved to path: ["+ screenshotPath + "]");

        return screenshot.getAbsolutePath();
    }
}
