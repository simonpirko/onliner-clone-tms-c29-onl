package by.tms.onlinerclonec29onl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Type type;
    private Role role;

    enum Type {
        PERSONAL, BUSINESS
    }

    enum Role {
        USER, ADMIN
    }
}
