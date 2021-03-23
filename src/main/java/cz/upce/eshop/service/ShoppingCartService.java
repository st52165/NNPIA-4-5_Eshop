package cz.upce.eshop.service;

import cz.upce.eshop.entity.Product;

import java.util.Map;

public interface ShoppingCartService {

    void add(long id);

    void remove(long id);

    Map<Product, Integer> getCart();
}
