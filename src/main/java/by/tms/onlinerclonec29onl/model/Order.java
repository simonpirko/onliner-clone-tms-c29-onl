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
public class Order {
    private Long id;
    private OrderItem orderItem;
    private double totalPrice;
    private OrderStatus status;

    public enum OrderStatus {
        OPEN, CLOSED, ARCHIVED
    }
}
