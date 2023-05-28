package org.itacademy.xpathtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XpathTest {

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
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("CATCH ElementNotInteractableException");
            throw new RuntimeException("CLICK ERROR");
        }
        try {
            element.sendKeys("TEST");
        } catch (ElementNotInteractableException e) {
            System.out.println("CATCH ElementNotInteractableException");
            throw new RuntimeException("SENDKEYS ERROR");
        }
    }

    @Test(testName = "multi test")
    public void multiTest() {
        String[][] pages_xpaths = new String[][]{
                {"https://www.pornhub.com/", "//input[@id=\"searchInput\"]"}, // серч бокс
                {"https://www.pornhub.com/", "//*[contains(text(), 'Categories')]"} // категории
//                {"https://tokiny.by/", "//*[@id=\"menu\"]//*[contains(text(), 'Доставка')]"}
        };
        List<String[]> tasks = new ArrayList<>();
        for (int i = 0; i < pages_xpaths.length; i++) {
            String[] task = new String[] {pages_xpaths[i][0], pages_xpaths[i][1]};
            tasks.add(task);
        }
        List<Boolean> results =
                tasks.stream()
                        .peek(t -> System.out.println("RUN " + t[0] + " " + t[1]))
                        .map(t -> CompletableFuture.supplyAsync(() -> testExecutor(t[0], t[1])))
                        .collect(Collectors.collectingAndThen(Collectors.toList(), completableFutures -> completableFutures
                                .stream().map(CompletableFuture::join)))
                        .toList();
        System.out.println("RESULTS: " + results);
    }


    public Boolean testExecutor(String page, String path) {
        xpathTest(page, path);
        return true;
    }


}
