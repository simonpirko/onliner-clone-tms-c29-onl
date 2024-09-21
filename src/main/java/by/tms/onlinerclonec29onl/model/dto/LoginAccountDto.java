package by.tms.onlinerclonec29onl.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAccountDto {
    @NotBlank(message = "Not Blank")
    @NotEmpty(message = "Not Empty")
    private String username;
    @NotBlank(message = "Not Blank")
    @NotEmpty(message = "Not Empty")
    private String password;
}
