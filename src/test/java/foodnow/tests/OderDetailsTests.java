package foodnow.tests;

import com.foodnow.pages.AddProductPage;
import com.foodnow.pages.CartPage;
import com.foodnow.pages.LoginPage;
import com.foodnow.pages.OrderDetailsPage;
import foodnow.core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OderDetailsTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        new LoginPage(app.driver).loginExistedUser();
        new AddProductPage(app.driver).addProduct();
        new CartPage(app.driver).checkoutToCart();
    }


    @Test
    public void formPayment() {
        new OrderDetailsPage(app.driver)
                .enterAddress()
                .clickSubmitOrder();
    }
}
