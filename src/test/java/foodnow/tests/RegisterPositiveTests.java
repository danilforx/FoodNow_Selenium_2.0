package foodnow.tests;

import com.foodnow.pages.RegisterPage;
import com.foodnow.pages.TextBoxPage;
import com.foodnow.utils.DataProviders;
import foodnow.core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class RegisterPositiveTests extends TestBase {



    @BeforeMethod
    public void preCondition() {
        new RegisterPage(app.driver)
                .clickIconAuthorization()
                .clickRegistrButton();
    }

    @Test
    public void registerPositiveTest() {
        new RegisterPage(app.driver)
                .enterPersonalData("Tom", "Smith", System.currentTimeMillis() + "_qa@test.com", "Asd123Lkj@", "+4" + System.currentTimeMillis())
                .clickSubmitRegister()
                .verifyUserIsLoggedIn();
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
