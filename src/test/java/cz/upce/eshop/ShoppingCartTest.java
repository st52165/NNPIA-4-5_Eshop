package cz.upce.eshop;

import cz.upce.eshop.datafactory.ProductTestDataFactory;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.service.ShoppingCartService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ShoppingCartTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductTestDataFactory productTestDataFactory;

    @Test
    void addOneToShoppingCart() {
        productTestDataFactory.saveProduct("MujProdukt");

        List<Product> all = productRepository.findAll();

        long productId = all.get(0).getId();

        shoppingCartService.add(all.get(0).getId());

        //počet prvků v košíku - 1
        Assertions.assertThat(shoppingCartService.getCart().size()).isEqualTo(1);
        //obsahuje právě vložený produkt
        Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isTrue();
        //obsahuje vložený produkt v počtu = 1
        Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);

        shoppingCartService.add(productId);
        //obsahuje vložený produkt v počtu = 2
        Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

        shoppingCartService.add(productId);
        //obsahuje vložený produkt v počtu = 3
        Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(3);

        shoppingCartService.remove(productId);
        //obsahuje produkty vyjma odebraného v počtu = 2
        Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

        shoppingCartService.remove(productId);
        //obsahuje produkty vyjma odebraného v počtu = 1
        Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);

        shoppingCartService.remove(productId);
        //obsahuje produkty vyjma odebraného v počtu = 0
        Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isFalse();
    }
}
