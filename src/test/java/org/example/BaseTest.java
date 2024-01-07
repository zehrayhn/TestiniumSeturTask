package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;


public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void before()
    {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("disable-notifications");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));

    }

    @AfterEach
    public void tearDown()
    {
  //      driver.quit();
    }

    public <T extends BasePage> T goToUrl(T page, String url)
    {
        driver.get(url);
        return page;
    }
}
