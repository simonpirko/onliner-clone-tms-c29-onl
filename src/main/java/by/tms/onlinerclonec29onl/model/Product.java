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
public class Product {
    private long id;
    private String name;
    private String description;
    private String category;
    private byte[] image;
}
