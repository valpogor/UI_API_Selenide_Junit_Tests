package ui.pageObject;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class CartPage {

    //BUTTONS
    private static final SelenideElement checkoutButton = $("#checkout").as("Checkout button");
    private static final ElementsCollection removeButton = $$x("//button[contains(@data-test, 'remove')]").as("Remove button");
    public static final SelenideElement cartButton = $(".shopping_cart_link").as("Shopping cart button");


    public static final SelenideElement cartBadge = $(".shopping_cart_badge").as("Shopping cart badge");
    public static final SelenideElement inventoryItem = $(".inventory_item_name").as("Inventory item");


    @Step("Click to checkout button")
    public static void clickToCheckoutButton() {

        checkoutButton.click();

    }

    @Step("Click to remove button")
    public static void clickToRemoveButton() {
        
        removeButton.stream().forEach(a -> a.click());

    }


}

