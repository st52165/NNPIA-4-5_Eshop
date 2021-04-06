package cz.upce.eshop.ui;

import cz.upce.eshop.datafactory.Creator;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
public class ProductUITest {

    private WebDriver driver;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        URL url = ProductUITest.class.getResource("/chromedriver/chromedriver.exe");
        String chromeDriverPath = null;
        try {
            chromeDriverPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();

        Product product = productRepository.findProductByNameContains("Nůžky");
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addProductTest() {
        singleProductAddTest();
    }

    @Test
    public void addProductTest2() {
        singleProductAddTest();
    }

    private void singleProductAddTest() {
        driver.get("http://localhost:8080/product-form");
        driver.findElement(By.id("name")).sendKeys("Nůžky");
        driver.findElement(By.id("description")).sendKeys("Nůžky obecné (tzv. střihavky)");
        driver.findElement(By.id("image")).sendKeys("D:\\Dokumenty\\Skola\\UPCE\\Magisterské studium\\1. Prvák\\" +
                "NNPIA (Programování internetových aplikací)\\Cvičení\\5\\eshop\\images\\nuzky.jpg");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam produktů']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h4[text()='Nůžky']")).size());
    }
}