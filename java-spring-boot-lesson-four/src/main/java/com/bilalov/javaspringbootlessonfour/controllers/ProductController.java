package com.bilalov.javaspringbootlessonfour.controllers;

import com.bilalov.javaspringbootlessonfour.entities.Basket;
import com.bilalov.javaspringbootlessonfour.entities.Product;
import com.bilalov.javaspringbootlessonfour.services.BasketService;
import com.bilalov.javaspringbootlessonfour.services.ProductService;
import com.bilalov.javaspringbootlessonfour.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @GetMapping
    public String indexPage(Model model,
                            @RequestParam(name = "titleFilter", required = false) Optional<String> titleFilter,
                            @RequestParam(name = "min", required = false) Optional<BigDecimal> min,
                            @RequestParam(name = "max", required = false) Optional<BigDecimal> max,
                            @RequestParam(name = "page", required = false) Optional<Integer> page,
                            @RequestParam(name = "size", required = false) Optional<Integer> size,
                            @RequestParam(name = "sortField", required = false) Optional<String> sortField,
                            @RequestParam(name = "sortOrder", required = false) Optional<String> sortOrder
                            ) {
        model.addAttribute("products",
                productService.getByParams(
                        titleFilter,
                        min,
                        max,
                        page,
                        size,
                        sortField,
                        sortOrder));
        return "product_views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              Model model) {
        model.addAttribute("product", productService.getById(id).orElseThrow(NotFoundException::new));
        return "product_views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productService.addOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_views/product_form";
    }

    @GetMapping("/to_basket/{id}")
    public String addToBasket(@PathVariable(value = "id") Long id) {
        Product product = productService.getById(id).get();
        Basket basket = new Basket();
        basket.setTitle(product.getTitle());
        basket.setDescription(product.getDescription());
        basket.setPrice(product.getPrice());
        basketService.addOrUpdate(basket);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.remove(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}


