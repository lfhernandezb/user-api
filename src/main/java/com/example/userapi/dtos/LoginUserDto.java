package com.example.userapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserDto {
    @NotEmpty(message = "The email is required.")
    @Email(message = "The email address is invalid.",
            flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;
    @NotEmpty(message = "The password is required.")
    @Size(min = 2, max = 16, message = "The length of the password must be between 2 and 16 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE },
            message = "The password must have minimum eight characters, at least one letter and one number.")
    private String password;
}
