package cz.upce.eshop.service;

import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Product, Integer> cart;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        cart = new HashMap<>();
    }

    @Override
    public void add(long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }
}
