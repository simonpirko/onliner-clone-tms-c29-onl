package by.tms.onlinerclonec29onl.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {
    private Long id;
    private String name;
    private String username;
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
