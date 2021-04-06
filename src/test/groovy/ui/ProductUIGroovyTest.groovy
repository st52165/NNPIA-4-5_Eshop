package ui

import cz.upce.eshop.EshopApplication
import cz.upce.eshop.datafactory.Creator
import cz.upce.eshop.entity.Product
import cz.upce.eshop.repository.ProductRepository
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
public class ProductUIGroovyTest {

    private WebDriver driver;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        URL url = ProductUIGroovyTest.class.getResource("/chromedriver/chromedriver.exe");
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
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void productList() {
        creator.saveEntities(new Product(name: "product1"), new Product(name: "product2"), new Product(name: "product3"));

        driver.get("http://localhost:8080/");
        Assert.assertEquals(1, driver.findElements(By.xpath("//h4[text()='product1']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h4[text()='product2']")).size());
        Assert.assertEquals(1, driver.findElements(By.xpath("//h4[text()='product3']")).size());

        //nakonec smažu vytvořené produkty
        productRepository.delete(productRepository.findProductByNameContains("product1"));
        productRepository.delete(productRepository.findProductByNameContains("product2"));
        productRepository.delete(productRepository.findProductByNameContains("product3"));
    }
}