package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
public class SellerProduct {
    private Long id;
    private Product product;
    private Shop shop;
    private double price;
}
