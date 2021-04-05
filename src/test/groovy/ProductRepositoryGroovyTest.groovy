package cz.upce.eshop

import cz.upce.eshop.datafactory.Creator
import cz.upce.eshop.entity.Product
import cz.upce.eshop.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(Creator.class)
public class ProductRepositoryGroovyTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Creator creator;

    @Test
    void saveProductTest() {

        Product testProduct = new Product("MujProdukt");
        creator.save(testProduct);

        List<Product> allProducts = productRepository.findAll();
        Assertions.assertThat(allProducts.size()).isEqualTo(11);

        def readFromDb = productRepository.findById(testProduct.getId()).get();
        Assertions.assertThat(readFromDb.getName()).isEqualTo("MujProdukt");
        Assertions.assertThat(readFromDb.getDescription()).isEqualTo("Test description");

        Assertions.assertThat(readFromDb.getSupplier().name).isEqualTo("Test name");
    }


}
