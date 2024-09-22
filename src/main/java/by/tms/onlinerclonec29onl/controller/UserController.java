package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Account account = accountService.findById(id).orElseThrow(() -> new IllegalArgumentException("Не верный ID:" + id));
        model.addAttribute("account", account);
        return "user";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("account") Account account) {
        if (account.getRole() == null) {
            account.setRole(Account.Role.USER);
        }
        accountService.updateAccount(account);
        return "redirect:/profile/" + account.getId();
    }
}