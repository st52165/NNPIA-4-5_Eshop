package cz.upce.eshop.service;

import cz.upce.eshop.entity.Product;

import java.util.Map;

public interface ShoppingCartService {

    void Add(long id);
    void Remove(long id);

    Map<Product, Integer> getCart();
}
