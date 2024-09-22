package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private final AccountService accountService;

    @Autowired
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (accountOptional.isPresent()) {
            model.addAttribute("account", accountOptional.get());
            return "user";
        } else {
            throw new IllegalArgumentException("Неверный ID пользователя: " + id);
        }
    }


    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("account") Account account) {
        Optional<Account> existingAccount = accountService.findById(account.getId());

        if (existingAccount.isPresent()) {
            Account updatedAccount = existingAccount.get();
            updatedAccount.setName(account.getName());
            updatedAccount.setUsername(account.getUsername());
            updatedAccount.setType(account.getType());
            updatedAccount.setRole(account.getRole());

            accountService.updateAccount(updatedAccount);
        } else {

            accountService.updateAccount(account);
        }

        return "redirect:/user/" + account.getId();
    }
}