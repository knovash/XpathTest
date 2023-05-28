package org.itacademy.xpathtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class XpathTest {

    @DataProvider()
    public Object[][] page_path() {
        return new Object[][]{
                /**
                 * xpath fast test
                 * insert the { PAGE URL, XPATH } into the dataprovider
                */

//                {"https://donerking.by/", "//img[@alt='Пончики с шоколадным кремом']"},
//                {"https://alexstar.ru/", "//*[@id=\"content\"]/div/div[2]/a[3]"},
//                {"https://alexstar.ru/smarthome/", "//*[@id=\"content\"]/form/div/div[1]/input"},
//                {"https://donerking.by/", "//*[@id=\"mainheader\"]/div[1]/div/div[1]/div[1]/div[2]/a"}
                //*[@id="app"]/div/div/div[2]/div/div[2]/div/div[2]/svg
//                {"https://demoqa.com", "//*[@id=\"app\"]/div/div/div[2]/div/div[2]/div/div[2]"}
                //li[@class="pcVideoListItem js-pop videoblock"]
//                {"https://www.pornhub.com/", "//input[@id=\"searchInput\"]"} // серч бокс
                //*[contains(text(), 'Categories')]
//                {"https://www.pornhub.com/", "//*[contains(text(), 'Categories')]"} // категории
                //*[@id="menu"]//*[contains(text(), 'Доставка')]
//                {"https://tokiny.by/", "//*[@id=\"menu\"]//*[contains(text(), 'Доставка')]"}

                //*[@id="page-content"]/div[2]/div/div[1]/div[2]/div[5]/a/div[1]
                {"https://tokiny.by/", "//*[@id=\"page-content\"]/div[2]/div/div[1]/div[2]/div[5]/a/div[1]"}
        };
    }

    @Test(testName = "xpath test", dataProvider = "page_path")
    public void xpathTest(String page, String path) {
        System.setProperty("webdriver.chrome.driver", "/home/konstantin/Downloads/chromedriver_linux64 113/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(page);
        driver.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement element = driver.findElement(By.xpath(path));
        System.out.println("PAGE: " + page);
        System.out.println("ELEMENT DISPLAYED: " + element.isDisplayed());
        System.out.println("ELEMENT TEXT: " + element.getText());
        System.out.println("ELEMENT CLASS: " + element.getClass());
        System.out.println("ELEMENT NAME: " + element.getAccessibleName());
        System.out.println("ELEMENT TAG: " + element.getTagName());
        String tag = element.getTagName();
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("CATCH ElementNotInteractableException");
//            throw new RuntimeException("CLICK ERROR");
        }
        if (tag.equals("input")) {
            System.out.println("TRY SENDKEYS...");
            element.sendKeys("TEST");
        }
    }
}
