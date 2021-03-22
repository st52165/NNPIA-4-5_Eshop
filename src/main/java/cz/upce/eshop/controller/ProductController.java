package cz.upce.eshop.controller;

import cz.upce.eshop.dto.AddOrEditProductDto;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "error";
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productRepository.findAll());

        return "product_list";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).get());
        return "product_detail";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String showProductForm(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("product", new AddOrEditProductDto());
        } else {
            Product product = productRepository.findById(id).orElse(new Product());
            model.addAttribute("product", product);
        }
        return "product_form";
    }

    @PostMapping("/product-form-process")
    public String ProductFormProcess(AddOrEditProductDto addOrEditProductDto) {
        Product product = new Product();
        product.setId(addOrEditProductDto.getId());
        product.setName(addOrEditProductDto.getName());
        productRepository.save(product);
        return "redirect:/";
    }
}
