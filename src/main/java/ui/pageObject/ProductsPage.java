package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static ui.pageObject.CartPage.clickToCheckoutButton;
import static ui.pageObject.CartPage.clickToRemoveButton;
import static ui.pageObject.CheckoutInformationPage.clickContinueButton;
import static ui.pageObject.CheckoutOverviewPage.clickToCancelButton;
import static ui.pageObject.CheckoutOverviewPage.clickToFinishButton;

public class ProductsPage {

    //BUTTONS
    public static SelenideElement addToCartBackpack = $("#add-to-cart-sauce-labs-backpack").as("Add To Cart Backpack");
    static SelenideElement addToCartBikeLight = $("#add-to-cart-sauce-labs-bike-light").as("Add To Cart Bike Light");
    public static final SelenideElement cartButton = $(".shopping_cart_link").as("Cart Button");

    public static final SelenideElement products = $(".title").as("Product Title");

    @Step("Add to cart")
    public static void addToCart() {
        addToCartBackpack.click();
        addToCartBikeLight.click();
        cartButton.click();
    }

    @Step("Shopping")
    public static void shopping() {
        addToCart();
        clickToCheckoutButton();
        clickContinueButton();
    }
}

