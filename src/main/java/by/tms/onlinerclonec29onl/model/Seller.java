package by.tms.onlinerclonec29onl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
@NoArgsConstructor
public class Seller {
    private int id;
    private String name;
    private String userName;
    private String password;
}
