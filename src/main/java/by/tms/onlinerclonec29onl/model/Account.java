package by.tms.onlinerclonec29onl.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {
    private Long id;
    @NotBlank(message = "www")
    @NotEmpty(message = "qqq")
    private String name;
    @NotBlank(message = "www")
    @NotEmpty(message = "qqq")
    private String username;
    @NotBlank(message = "www")
    @NotEmpty(message = "qqq")
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
