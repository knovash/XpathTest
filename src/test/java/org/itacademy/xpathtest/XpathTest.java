package org.itacademy.xpathtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XpathTest {

//    @DataProvider()
//    public Object[][] page_path() {
//        return new Object[][]{
//                {"https://demoqa.com/automation-practice-form", "//*[@id='react-select-3-input']"},
//                {"https://demoqa.com/automation-practice-form", "//*[@id='currentAddress']"}
//        };
//    }

//    @Test(testName = "xpath test", dataProvider = "page_path")
    @Test(testName = "xpath test")
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


    @Test(testName = "multi test")
    public void multiTest() {

//        System.out.println("ELEMENT CLASS: ");
//        String[] ip_array = new String[]{"бургер", "донер", "пончики"};

        String pagex = "https://demoqa.com/automation-practice-form";

//        Object[][] pages_xpaths = new Object[][]{
//                {"https://demoqa.com/automation-practice-form", "//*[@id='react-select-3-input']"},
//                {"https://demoqa.com/automation-practice-form", "//*[@id='currentAddress']"}
//        };

        String[] xpaths = new String[]{
                "//*[@id='react-select-3-input']",
                "//*[@id='currentAddress']"
        };


        List<String> listof = List.of(xpaths);
        List<Boolean> result2 =
                listof.stream()
                        .map(item -> CompletableFuture.supplyAsync(() -> testExecutor(pagex, item)))
                        .collect(Collectors.collectingAndThen(Collectors.toList(), cfs -> cfs.stream().map(CompletableFuture::join)))
                        .toList();
        System.out.println("\nRESULT 2 : " + result2);

    }


    public Boolean testExecutor(String page, String path) {
        System.out.println("RUN TEST page: " + page + " xpath: " + path);
        xpathTest(page, path);
        return true;
    }


}
