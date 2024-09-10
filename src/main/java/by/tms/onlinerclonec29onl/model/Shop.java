package by.tms.onlinerclonec29onl.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Shop {
    private Long id;
    private String title;
    private String description;
    private Account creator;
}
