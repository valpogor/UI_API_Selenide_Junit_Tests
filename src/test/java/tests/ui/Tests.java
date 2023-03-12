package tests.ui;

import org.junit.jupiter.api.*;
import static ui.pageObject.CartPage.*;
import static ui.pageObject.CheckoutCompletePage.*;
import static ui.pageObject.CheckoutInformationPage.*;
import static ui.pageObject.CheckoutOverviewPage.*;
import static ui.pageObject.LoginPage.*;
import io.qameta.allure.*;
import utils.UIBaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Tests extends UIBaseTest {

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(1)
    @DisplayName("Login")
    @Description("Test Login functionality")
    public void testLogin() {
        login();
        elementVisible(products, true);
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/checkout-complete.html")
    @Test
    @Order(2)
    @DisplayName("Shopping")
    @Description("Test Shopping functionality")
    public void testShopping() {
        login();
        shopping();
        clickToFinishButton();
        elementVisible(thankYouOrderMessage, true);
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(3)
    @DisplayName("Cancellation of purchase")
    @Description("Test of Cancellation of purchase functionality")
    public void testCancellationOfPurchase() {
        login();
        shopping();
        clickToCancelButton();
        clickToRemoveButton();
        elementVisible(products, true);
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/cart.html")
    @Test
    @Order(4)
    @DisplayName("Removing all items from the cart")
    @Description("Test of Removing all items from the cart functionality")
    public void testRemovingAllItemsFromTheCart() {
        login();
        addToCart();
        clickToRemoveButton();
        elementVisible(cartBadge, false);
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/checkout-step-two.html")
    @Test
    @Order(5)
    @DisplayName("Сalculation of the total amount of items")
    @Description("Test of Сalculation of the total amount of items functionality")
    public void testCalculationOfTheTotalAmountOfItems(){
        login();
        addToCart();
        clickToCheckoutButton();
        clickContinueButton();
        elementVisible(cartBadge, true);
    }
}

