package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
public class Customer {
    private long id;
    private String name;
    private String userName;
    private String password;
    private Cart cart;
}
