package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;


public class WebFormTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Позитивные сценарии


    @Test
    void shouldTestNormalCss() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Иванов");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79161234567");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    void shouldTestNameWithSpace() {
        driver.get("http://localhost:9999/");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Иванов Иван");
        textFields.get(1).sendKeys("+71231234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    void shouldTestNameWithDash() {
        driver.get("http://localhost:9999/");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Мамин-Сибиряк");
        textFields.get(1).sendKeys("+71231234567");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.trim());
    }

}