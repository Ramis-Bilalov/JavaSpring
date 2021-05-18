package com.bilalov.javaspringbootlessonfour.controllers;

import com.bilalov.javaspringbootlessonfour.services.BasketService;
import com.bilalov.javaspringbootlessonfour.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
public class BasketController {

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
        model.addAttribute("basket",
                basketService.getByParams(
                        titleFilter,
                        min,
                        max,
                        page,
                        size,
                        sortField,
                        sortOrder));
        return "product_views/basket_form";
    }
}
