package cz.upce.eshop;

import cz.upce.eshop.datafactory.ProductTestDataFactory
import cz.upce.eshop.datafactory.SupplierTestDataFactory;
import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.OrderHasProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.StateEnum;
import cz.upce.eshop.repository.OrderHasProductRepository;
import cz.upce.eshop.repository.OrderRepository;
import cz.upce.eshop.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import([ProductTestDataFactory.class, SupplierTestDataFactory.class])
public class ProductRepositoryGroovyTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTestDataFactory productTestDataFactory;

    @Test
    void saveProductTest() {

        Product testProduct = new Product("MujProdukt");
        productTestDataFactory.saveProduct(testProduct);

        List<Product> allProducts = productRepository.findAll();
        Assertions.assertThat(allProducts.size()).isEqualTo(11);

        def readFromDb = productRepository.findById(testProduct.getId()).get();
        Assertions.assertThat(readFromDb.getName()).isEqualTo("MujProdukt");
        Assertions.assertThat(readFromDb.getDescription()).isEqualTo("Test description");
    }


}
