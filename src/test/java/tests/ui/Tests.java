package tests.ui;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import pageObject.*;
import static com.codeborne.selenide.Condition.visible;
import io.qameta.allure.*;
import utils.BaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Tests extends BaseTest {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("resources/screenshots");
    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(1)
    @DisplayName("Login")
    public void testLogin() {
        LoginPage.login();
        ProductsPage.products.shouldBe(visible);
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/checkout-complete.html")
    @Test
    @Order(2)
    @DisplayName("Shopping")
    public void testShopping() {
        LoginPage.login();
        ProductsPage.addToCart();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.clickContinueButton();
        CheckoutOverviewPage.clickToFinishButton();
        CheckoutCompletePage.thankYouOrderMessage.shouldBe(visible);
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(3)
    @DisplayName("Cancellation of purchase")
    public void testCancellationOfPurchase() {
        LoginPage.login();
        ProductsPage.addToCart();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.clickContinueButton();
        CheckoutOverviewPage.clickToCancelButton();
        CartPage.clickToRemoveButton();
        ProductsPage.products.shouldBe(visible);

    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/cart.html")
    @Test
    @Order(4)
    @DisplayName("Removing all items from the cart")
    public void testRemovingAllItemsFromTheCart() {
        LoginPage.login();
        ProductsPage.addToCart();
        CartPage.clickToRemoveButton();
        CartPage.cartBadge.shouldNotBe(visible);
        CartPage.cartButton.getText();
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/checkout-step-two.html")
    @Test
    @Order(5)
    @DisplayName("Сalculation of the total amount of items")
    public void testCalculationOfTheTotalAmountOfItems() {
        LoginPage.login();
        ProductsPage.addToCart();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.clickContinueButton();
    }

}

