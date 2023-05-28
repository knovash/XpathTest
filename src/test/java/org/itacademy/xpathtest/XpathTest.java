package org.itacademy.xpathtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XpathTest {

    // метод тестирования на странице PAGE икспаса PATH.  будет для каждого браузера запускаться в отдельном потоке
    public void xpathTest(String page, String path) {
        System.setProperty("webdriver.chrome.driver", "/home/konstantin/Downloads/chromedriver_linux64 113/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(page);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement element = driver.findElement(By.xpath(path));
        System.out.println(page + " PAGE");
        System.out.println(page + " ELEMENT DISPLAYED: " + element.isDisplayed());
        System.out.println(page + " TEXT: " + element.getText());
        System.out.println(page + " ELEMENT CLASS: " + element.getClass());
        System.out.println(page + " ELEMENT NAME: " + element.getAccessibleName());
        System.out.println(page + " ELEMENT TAG: " + element.getTagName());
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            System.out.println(page + " CATCH click error");
//            throw new RuntimeException("CLICK ERROR");
        }
        try {
            element.sendKeys("TEST");
        } catch (ElementNotInteractableException e) {
            System.out.println(page + " CATCH sendkeys error");
//            throw new RuntimeException("SENDKEYS ERROR");
        }
    }

    @Test(testName = "multithread test executor")
    public void multiTest() {
        // массив { TEST PAGE, TEST XPATH}
        String[][] pages_xpaths = new String[][]{
                {"https://donerking.by/", "//div[@id='filters']//button[contains(text(), 'Донеры')]"}
                , {"https://www.google.com/", "//textarea[@class='gLFyf']"}
                , {"https://demoqa.com/automation-practice-form", "//input[@id='firstName']"}
        };
        List<String[]> tasks = new ArrayList<>();
        // массив в коллекцию для стрима
        for (int i = 0; i < pages_xpaths.length; i++) {
            String[] task = new String[]{pages_xpaths[i][0], pages_xpaths[i][1]};
            tasks.add(task);
        }

        // стрим коллекции.
        tasks.stream()
                .peek(t -> System.out.println("RUN " + t[0] + " " + t[1]))
                // методы теста xpathTest запускаются в отдельных потоках CompletableFuture с параметрами из коллекции
                .map(t -> CompletableFuture.runAsync(() -> xpathTest(t[0], t[1])))
                // собирается коллекция запущеных комплитаблфюч
                .collect(Collectors.collectingAndThen(Collectors.toList(), completableFutures -> completableFutures
                        // join дожидается каждой завершенной комплитаблфючи
                        .stream().map(CompletableFuture::join)))
                .toList();
    }
}
