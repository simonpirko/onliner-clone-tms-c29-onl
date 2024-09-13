package by.tms.onlinerclonec29onl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    private long id;
    private Product product;
    private int quantity;
    private double price;
}
