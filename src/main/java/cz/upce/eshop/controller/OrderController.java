package cz.upce.eshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class OrderController {
    @GetMapping("/checkout")
    public String checkout(Model model) {

    }
}
