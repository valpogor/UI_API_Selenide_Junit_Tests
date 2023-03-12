package ui.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ui.pageObject.CheckoutCompletePage.thankYouOrderMessage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

public class LoginPage extends ProductsPage {

    //LOGIN FORM FIELDS
    public static final SelenideElement usernameField = $("#user-name").as("Username Field");
    public static final SelenideElement passwordField = $("#password").as("Password Field");

    //BUTTON
    public static final SelenideElement loginButton = $("#login-button").as("Login Button");

    //DATA
    public static final CharSequence USERNAME = (PropertyReader.getInstance().getUsername());
    public static final CharSequence PASSWORD = (PropertyReader.getInstance().getPassword());

    @Step("Login")
    public static void login() {
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
    }

    @Step("Verify element is visible")
    public static void elementVisible(SelenideElement element, Boolean visibility) {
        if(visibility!=false){ element.shouldBe(visible);}
        else{ element.shouldNotBe(visible);}
    }

    @Step("Open login page")
    public static void openLoginPage(){
        open(PropertyReader.getInstance().getURL());
    }
}

