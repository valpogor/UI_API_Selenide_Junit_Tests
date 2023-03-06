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

    }
}

