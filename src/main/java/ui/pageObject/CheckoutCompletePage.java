package ui.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {

    //MESSAGE
    public static SelenideElement thankYouOrderMessage = $(".complete-header").as("Thank You Order Message");

}
