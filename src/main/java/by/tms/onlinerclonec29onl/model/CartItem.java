package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.math.BigDecimal;

@Data
@ControllerAdvice
@NoArgsConstructor
public class CartItem {
    private Long id;
    private SellerProduct sellerProduct;
    private int quantity;
    private BigDecimal amount;
}
