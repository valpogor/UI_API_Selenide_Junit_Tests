package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.*;
import static utils.PropertyReader.*;

public class RegisterTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("successfulRegister")
    public void successfulRegister() {
        register(email, job, 200);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulRegister")
    public void unSuccessfulRegister() {
        register(email, "", 400);
    }
}
