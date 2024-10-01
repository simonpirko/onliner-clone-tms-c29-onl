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
public class Order {
    private Long id;
    //private OrderItem orderItem;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String delivery_address;
    private Long accountId;

    public enum OrderStatus {
        OPEN, CLOSED, ARCHIVED
    }
}
