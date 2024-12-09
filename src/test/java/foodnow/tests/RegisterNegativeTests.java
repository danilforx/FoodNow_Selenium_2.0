package foodnow.tests;

import com.foodnow.pages.RegisterPage;
import com.foodnow.pages.TextBoxPage;
import com.foodnow.utils.DataProviders;
import foodnow.core.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class RegisterNegativeTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new RegisterPage(app.driver)
                .clickIconAuthorization()
                .clickRegistrButton();
    }

    @Test
    public void registerNegativeTest() {
        String invalidFirstName = "t";
        String invalidLastName = "r";
        String invalidEmail = "invalidEmail";
        String invalidPassword = "123";
        String invalidPhone = "12345";

        new RegisterPage(app.driver)
                .enterPersonalData(invalidFirstName, invalidLastName, invalidEmail, invalidPassword, invalidPhone)
                .clickSubmitRegister()
                .failAuthorizationLogin("First name", "Last name", "Email","Password","Phone number");
    }

    @Test(dataProvider = "invalidRegistrationData", dataProviderClass = DataProviders.class)
    public void registerNegativeTest1(String firstName, String lastName, String email, String password, String phone) {
        logger.info("[RegisterNegativeTests] Starting negative registration test with data: firstName={}, lastName={}, email={}, password={}, phone={}",
                firstName, lastName, email, password, phone);
        new RegisterPage(app.driver)
                .enterPersonalData(firstName, lastName, email, password, phone)
                .clickSubmitRegister();

        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(5)); // Используем app.driver
        boolean isRegistrationFailed = wait.until(ExpectedConditions.urlContains("#/registration"));

        Assert.assertTrue(isRegistrationFailed, "Registration unexpectedly succeeded with invalid data");
    }
}