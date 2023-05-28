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
//                {"https://tokiny.by/", "//*[@id=\"page-content\"]/div[2]/div/div[1]/div[2]/div[5]/a/div[1]"}

                //*[@id="page-content"]/div[2]/div/div[1]/div[2]/div[5]/a
//                {"https://tokiny.by/", "//div[@style='background-image: url(https://tokiny.by/media/zoo/images/roll_new_f2348ed7712b7496e8af874311a209dc.png);']"}
//                {"https://tokiny.by/", "//div[contains(@style,'/zoo/images/roll_new_')]"}
                //*[@id="page-content"]/div[2]/div/div[1]/div[2]/div[5]/a

                //input[@id='firstName']
//                {"https://demoqa.com/automation-practice-form", "//input[@id='firstName']"}

                //div[@class='subjects-auto-complete__placeholder css-1wa3eu0-placeholder']
//        class="subjects-auto-complete__control css-yk16xz-control"

//                css-2b097c-container
//                subjects-auto-complete__control css-yk16xz-control
//                subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3
//                subjects-auto-complete__placeholder css-1wa3eu0-placeholder
//                subjects-auto-complete__input
//                {"https://demoqa.com/automation-practice-form", "//div[@class='subjects-auto-complete__placeholder css-1wa3eu0-placeholder']"}

//                {"https://demoqa.com/automation-practice-form", "//*[@id='react-select-3-input']"}

                {"https://demoqa.com/automation-practice-form", "//*[@id='currentAddress']"}
        };
    }

    @Test(testName = "xpath test", dataProvider = "page_path")
    public void xpathTest(String page, String path) {
        System.setProperty("webdriver.chrome.driver", "/home/konstantin/Downloads/chromedriver_linux64 113/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(page);
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
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

//        try {
//            element.click();
//        } catch (ElementNotInteractableException e) {
//            System.out.println("CATCH ElementNotInteractableException");
//            throw new RuntimeException("CLICK ERROR");
//        }

        try {
            element.sendKeys("NCR");
        } catch (ElementNotInteractableException e) {
            System.out.println("CATCH ElementNotInteractableException");
            throw new RuntimeException("SENDKEYS ERROR");
        }

    }
}
