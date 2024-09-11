package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
public class Order {
    private long id;

    private OrderItem orderItem;
    private double totalPrice;
    private String status;
}
