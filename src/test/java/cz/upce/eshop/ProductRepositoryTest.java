package cz.upce.eshop;

import cz.upce.eshop.datafactory.Creator;
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
@Import(Creator.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    @Autowired
    private Creator creator;

    @Test
    void saveProductTest() {
        Product product = new Product("MujProdukt");
        creator.save(product);


        List<Product> allProducts = productRepository.findAll();
        Assertions.assertThat(allProducts.size()).isEqualTo(11);

        Order order = new Order();
        order.setState(StateEnum.NEW);
        orderRepository.save(order);

        OrderHasProduct orderHasProduct = new OrderHasProduct();
        orderHasProduct.setProduct(product);
        orderHasProduct.setAmount(5);
        orderHasProduct.setOrder(order);

        OrderHasProduct orderHasProduct2 = new OrderHasProduct();
        orderHasProduct2.setProduct(product);
        orderHasProduct2.setAmount(5);
        orderHasProduct2.setOrder(order);

        orderHasProductRepository.save(orderHasProduct);
        orderHasProductRepository.save(orderHasProduct2);

        List<Order> all = orderRepository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(11);
    }


}
