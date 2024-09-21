package by.tms.onlinerclonec29onl.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/user")
    public String getUser() {

        return "user";
    }
}
