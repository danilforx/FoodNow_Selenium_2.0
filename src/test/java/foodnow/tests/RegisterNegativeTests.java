package foodnow.tests;

import com.foodnow.pages.RegisterPage;
import com.foodnow.pages.TextBoxPage;
import com.foodnow.utils.DataProviders;
import foodnow.core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class RegisterNegativeTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new RegisterPage(app.driver)
                .clickIconAuthorization()
                .clickRegistrButton();
    }

    @Test
    public void registerNegativeTest() {
        String invalidEmail = "invalidEmail";
        String invalidPassword = "123";
        String invalidPhone = "12345";

        new RegisterPage(app.driver)
                .enterPersonalData("Tom", "Smith", invalidEmail, invalidPassword, invalidPhone)
                .clickSubmitRegister()
                .failAuthorizationLogin("Email is not valid", "Password field should contain minimum 6 symbols", "Phone number is not valid");
    }

    @Test(dataProvider = "addNewUserFormFromCSVFile", dataProviderClass = DataProviders.class)
    public void keyboardEventDataProviderTest(String firstName, String lastName, String email, String password, String phone) throws AWTException {
        new TextBoxPage(app.driver)
                .enterPersonalData(firstName, lastName, email, password, phone)
        //.keyboardEvent()
        //.verifyCopyPasteAddress()
        ;
    }
}