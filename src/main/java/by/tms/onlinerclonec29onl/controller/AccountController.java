package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.dto.LoginAccountDto;
import by.tms.onlinerclonec29onl.service.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginAccountDto", new LoginAccountDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginAccountDto loginAccountDto, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Optional<Account> account = accountService.login(loginAccountDto);
        if (account.isPresent()) {
            session.setAttribute("account", account.get());
            return "redirect:/";
        }
        model.addAttribute("account", account);
        model.addAttribute("errorMessage", "Invalid username or password");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("account", new Account());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        accountService.register(account);
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setUsername(account.getUsername());
        model.addAttribute("loginAccountDto", loginAccountDto);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
