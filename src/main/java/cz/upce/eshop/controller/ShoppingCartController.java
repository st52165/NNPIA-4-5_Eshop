package cz.upce.eshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.jws.WebParam;

public class ShoppingCartController {
    @GetMapping("/shopping-cart-add/{id}")
    public String shoppingCartAdd(@PathVariable long id, Model model) {

        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart-remove/{id}")
    public String shoppingCartRemove(@PathVariable long id, Model model) {

        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model) {

        return "/shopping-cart";
    }

}
