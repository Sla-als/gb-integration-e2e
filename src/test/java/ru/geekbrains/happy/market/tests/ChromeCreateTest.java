package ru.geekbrains.happy.market.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeCreateTest {
    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    /**
     * 5.6. Написать тест для проверки смены страницы:
     * проверка, что заголовок страницы меняется на "Google",
     * когда пользователь переходит на "https://www.google.com/"
     */
    @Test
    void test() {
        // open page
        driver.get("https://www.google.com/");
        // check title
        assertEquals(driver.getTitle(), "Google");
        // close browser
        driver.quit();
    }

    /**
     * 5.7. Написать тест для проверки поиска:
     * проверка, что страница результатов поиска содержит искомое название,
     * когда пользователь переходит на "https://www.google.com/" и вводит в поиске (q) GeekBrains
     */
    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com/");
        // q - поиск
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("GeekBrains");
        element.submit();
        String title = driver.getTitle();
        assertTrue(title.contains("GeekBrains"));
        driver.quit();
    }

    /**
     * HW5.1. Напишите тест, который будет проверять, что сайт https://gb.ru/ работает корректно.
     * Проверяем, что сайт называется корректно,
     * проверяем, что URL-адрес сайта совпадает с указанным
     */
    @Test
    public void testGBLoginForm() {
        driver.get("https://gb.ru/");
        String title = driver.getTitle();
        assertEquals(title, "GeekBrains - образовательный портал"); // Проверяем, что сайт называется корректно
        String url = driver.getCurrentUrl();
        assertEquals(url, "https://gb.ru/"); // Проверяем, что URL-адрес сайта совпадает с указанным
        driver.quit();
    }
}