package by.tms.onlinerclonec29onl.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {
    private Long id;
    private String title;
    private String description;
    private Account creator;
}