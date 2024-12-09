package foodnow.core;

import com.foodnow.core.ApplicationManager;
import com.foodnow.core.BaseHelper;
import com.foodnow.pages.CartPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
    WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite //Suite - запускается несколько раз
    public void start() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Required for CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        logger.info("*** TESTING IN PROGRESs ***");
        app.init();
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("test " + method.getName() + " started");
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result){
        if (result.isSuccess()){
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            logger.info("Test is FAILED: [" + method.getName() + "], Screenscot: [" + app.getBaseHelper().takeScreenshot() + "]");
        }
    }


    @AfterSuite(enabled = true)
    public void tearDown() {
        logger.info("*** TESTING Ended ***");
        app.stop(); //завершение работы браузера
    }
}
