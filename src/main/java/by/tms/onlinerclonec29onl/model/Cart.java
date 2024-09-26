package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Data
@ControllerAdvice
@NoArgsConstructor
public class Cart {
    private Long id;
    private Account account;
    private List<CartItem> products = new ArrayList<>();
}
