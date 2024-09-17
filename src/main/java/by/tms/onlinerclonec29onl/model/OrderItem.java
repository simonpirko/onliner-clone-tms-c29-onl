package by.tms.onlinerclonec29onl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.math.BigDecimal;

@Data
@ControllerAdvice
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    private Long id;
    private Product product;
    private int quantity;
    private BigDecimal price;
}
