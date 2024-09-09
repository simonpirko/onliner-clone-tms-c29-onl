package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@Data
@ControllerAdvice
@NoArgsConstructor
public class Cart {

    private long id;
    private Customer customer;
    private List<CartItem> products;
}
