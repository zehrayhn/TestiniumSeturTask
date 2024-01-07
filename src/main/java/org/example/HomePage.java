package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final Logger logger = LogManager.getLogger(HomePage.class);


    @FindBy(css = "div[class='ins-notification-content ins-notification-content-337'] div#wrap-close-button-1454703513202 div span")
    private WebElement bigCircle;
    @FindBy(css = "div a#CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll")
    private WebElement bigAllow;
    @FindBy(css = "div.sc-5391ca11-0.hxzxnh button:nth-child(1)")
    private WebElement firstButton;


    @FindBy(css = "[placeholder=\"Otel Adı Veya Konum\"]")
    private WebElement destinationLocator;

    @FindBy(css = "div[class=\"sc-94dcce44-10 hOQfGD\"] div[class=\"sc-acaf4d52-1 bmGRpe\"]")
    private WebElement otelTabLocator;

    @FindBy(name = "sc-149d934e-4 dwdxhf")
    private WebElement dateButton;

    @FindBy(css="[placeholder=\"Otel Adı Veya Konum\"]")
    private WebElement otelName;

    public HomePage closeBigCircle() {
        wait.until(ExpectedConditions.visibilityOf(bigCircle));
        bigCircle.click();
        return this;
    }

    public HomePage closeBigCookie() {
        wait.until(ExpectedConditions.visibilityOf(bigAllow));
        bigAllow.click();
        return this;
    }


    private void closeBigCircleOrAllowInternal() {

        if (isElementVisible(bigCircle)) {
            closeBigCircle();
            closeBigCookie();
        } else if (isElementVisible(bigAllow)) {
            closeBigCookie();
            closeBigCircle();
        }
    }

    private boolean isElementVisible(WebElement element) {
        try {
            if (element != null) {
                return true;
            }
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
            return false;
        }
    }

    public HomePage closeBigCircleOrAllow() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(bigCircle),
                ExpectedConditions.visibilityOf(bigAllow)
        ));

        closeBigCircleOrAllowInternal();
        return this;
    }



    public void dateTime() {
        try {
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/div/span[2]")).click();

            String mainHandle = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("Hata: " + e.getMessage());
        }
    }


    public String getFirstButtonClass() {
        wait.until(ExpectedConditions.visibilityOf(firstButton));
        return firstButton.getAttribute("class");
    }



    public void writeTextToLocationInput(String text) {

        try {
            wait.until(ExpectedConditions.visibilityOf(otelName)).click();
            destinationLocator.sendKeys(text);
            wait.until(ExpectedConditions.visibilityOf(otelName)).click();

            Thread.sleep(5000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));

            String mainWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();
            logger.info("System.out.println(allWindowHandles.size()) : "+allWindowHandles.size());

            for (String windowHandle : allWindowHandles) {
                if (!windowHandle.equals(mainWindowHandle)) {
                    System.out.println("Yeni Pencere Handle: " + windowHandle);
                    logger.info("yeni pencere " +windowHandle);
                    driver.switchTo().window(windowHandle);

                    driver.switchTo().window(mainWindowHandle);
                    break;
                }
            }


        } catch (Exception e) {
            logger.info("Hata mesajı: " + e.getMessage());
        }
    }


}
