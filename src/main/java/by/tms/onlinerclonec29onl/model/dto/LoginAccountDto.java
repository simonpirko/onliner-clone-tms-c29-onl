package by.tms.onlinerclonec29onl.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAccountDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
