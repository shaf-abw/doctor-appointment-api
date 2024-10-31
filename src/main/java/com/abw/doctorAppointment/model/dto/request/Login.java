package com.abw.doctorAppointment.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Login {

    @NotBlank(message = "The username must not be left blank")
    @Size(min = 6, max = 50, message = "The username must be 6 characters or more")
    private String username;

    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain all uppercase and lowercase letters and numbers")
    private String password;
}
