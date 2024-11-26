package foodnow.tests;

import com.foodnow.pages.LoginPage;
import foodnow.core.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPositiveTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        logger.info("*** LOGIN TESTING IN PROGRESS ***");
    }

    @Test
    public void loginPositiveTest() {
        logger.info("*** LOGIN POSITIVE TESTING IN PROGRESS ***");
        new LoginPage(app.driver)
                .clickIconAuthorization()
                .clickLoginButton()
                .enterPersonalData("fgg@jhfsd.com", "Asd123Lkj@")
                .clickSubmitLogin()
                .verifyUserIsLoggedIn();
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

