package by.tms.onlinerclonec29onl.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
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
