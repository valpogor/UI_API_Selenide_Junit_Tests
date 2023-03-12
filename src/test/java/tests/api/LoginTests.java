package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.login;
import static utils.PropertyReader.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests {
    @AllureId("1")
    @Feature("Login")
    @Tag("smoke")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Successful Login")
    @Description("Successful Login functionality")
    public void successfulLogin() {
        login(email, pwd, 200);
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulLogin")
    @Description("UnSuccessful Login functionality")
    public void unSuccessfulLogin() {
        login(email,"", 400);
    }
}
