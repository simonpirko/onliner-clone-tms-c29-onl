package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
public class CartItem {
    private long id;
    private Product product;
    private Seller seller;
    private int quantity;
    private double price;
}
