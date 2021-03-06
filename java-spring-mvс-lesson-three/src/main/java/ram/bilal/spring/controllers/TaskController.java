package ram.bilal.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ram.bilal.spring.model.Product;
import ram.bilal.spring.model.ProductRepository;

@Controller
@RequestMapping("/product")
public class TaskController {

    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "task-views/index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model ) {
        model.addAttribute("product", productRepository.findById(id));
        return "task-views/product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productRepository.update(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Product product) {
        productRepository.add(product);
        return "task-views/product_form"; // не заполненная!!!
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productRepository.remove(id);
        return "redirect:/product"; // не заполненная!!!
    }
}
