package by.tms.onlinerclonec29onl.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {
    private Long id;
    @NotBlank(message = "Not Blank")
    @NotEmpty(message = "Not Empty")
    private String name;
    @NotBlank(message = "Not Blank")
    @NotEmpty(message = "Not Empty")
    private String username;
    @NotBlank(message = "Not Blank")
    @NotEmpty(message = "Not Empty")
    private String password;
    private Type type;
    private Role role;

    public enum Type {
        PERSONAL, BUSINESS
    }

    public enum Role {
        USER, ADMIN
    }
}
