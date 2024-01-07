package org.example;

import org.apache.logging.log4j.LogManager;
import org.example.Helper.CSVHelper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest{
    HomePage homePage;
    CSVHelper csvHelper;
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);

    @Test
    public void testDefaultOtelTab()
    {
        homePage = goToUrl(new HomePage(driver, wait), "https://www.setur.com.tr/");
        String expectedURL = "https://www.setur.com.tr/";
        String actualURL = driver.getCurrentUrl();
        assertEquals(expectedURL, actualURL, "URL kontrolü başarısız: ");
        homePage.closeBigCircleOrAllow();
        String expectedDefaultClass = "sc-5391ca11-1 crUBM";

        String actualClass = homePage.getFirstButtonClass();
        logger.info("actualClass: " + actualClass);

        assertEquals(expectedDefaultClass, actualClass, "Seçili butonun default olarak seçili gelmesi kontrolü başarısız: ");

        csvHelper = new CSVHelper("src/data/SeturData.csv");

        try {
            String destination = csvHelper.getCellValue(1, 0);
            logger.info("csv " + destination);
            homePage.writeTextToLocationInput(destination);
            homePage.dateTime();
        } catch (Exception e) {
            logger.info("hata" + e.getMessage());
        }


    }


}
