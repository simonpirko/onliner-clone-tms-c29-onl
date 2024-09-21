package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserPageController {

    private final AccountService accountService;

    @Autowired
    public UserPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/profile/{id}")
    public String getUserProfile(@PathVariable("id") long id, Model model) {
        Optional<Account> accountOptional = accountService.findById(id);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            model.addAttribute("username", account.getUsername());
            model.addAttribute("name", account.getName());
            model.addAttribute("type", account.getType());

            return "profile";
        } else {
            return "error/404";  // Показать страницу ошибки, если аккаунт не найден
        }
    }
}