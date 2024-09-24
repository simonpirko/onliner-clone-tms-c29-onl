package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.dto.LoginAccountDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DuplicateKeyException.class)
    public String exceptionHandler(DuplicateKeyException ex, Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("errorMessage", "Имя пользователя уже существует!");
        return "registration";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String exceptionHandler(EmptyResultDataAccessException ex, Model model) {
        model.addAttribute("loginAccountDto", new LoginAccountDto());
        model.addAttribute("errorMessage", "Неверное имя пользователя или пароль!");
        return "login";
    }
}
