package by.tms.onlinerclonec29onl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserPageController {

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("username", "Иван Иванов");
        model.addAttribute("joinDate", "2024");
        model.addAttribute("email", "ivanov@example.com");
        model.addAttribute("phone", "+375 29 123-45-67");
        return "profile";
    }
}