package foodnow.tests;

import com.foodnow.pages.CartPage;
import com.foodnow.pages.LoginPage;
import foodnow.core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new LoginPage(app.driver).loginExistedUser();
        new CartPage(app.driver).addProductToCart();
    }


    @Test
    public void proceedToCheckout() {
        new CartPage(app.driver).checkoutToCart();
    }
}
