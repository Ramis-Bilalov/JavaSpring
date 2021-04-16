package ram.bilal.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloMethod(Model model) {
        model.addAttribute("name", "student");
        return "hello_view";
    }

    @GetMapping("/first")
    public String firstMethod(){
        return "first";
    }
}
