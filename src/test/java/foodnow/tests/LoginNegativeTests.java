package foodnow.tests;

import com.foodnow.pages.LoginPage;
import foodnow.core.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        logger.info("*** LOGIN TESTING IN PROGRESS ***");
    }



    @Test
    public void loginNegativeTestWithAssert() {
        new LoginPage(app.driver)
                .clickIconAuthorization()
                .clickLoginButton()
                .enterPersonalData("invalidEmail@gmail.com", "InvalidPassword")
                .clickSubmitLogin()
                .failAuthorLogin("Login or email is invalid. Try again.");
    }

    @AfterMethod
    public void postConditions(){
        try {
            new LoginPage(app.driver).logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
}

