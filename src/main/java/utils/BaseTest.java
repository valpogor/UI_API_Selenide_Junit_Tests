package utils;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {

    @BeforeAll
    public void setUp() {
        browser = PropertyReader.getInstance().getBrowser();
        browserSize = "1920x1080";
        headless = Boolean.parseBoolean(PropertyReader.getInstance().getHeadless());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        addListener(new WebEventListener());
    }

    @BeforeEach
    public void init() {
        open(PropertyReader.getInstance().getURL());
    }

    @AfterAll
    public void closeDriver() {
        closeWebDriver();
    }
}
