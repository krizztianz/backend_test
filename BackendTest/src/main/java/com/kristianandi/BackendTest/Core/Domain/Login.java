package com.kristianandi.BackendTest.Core.Domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Login {
    @Pattern(regexp = "^08[0-9]+", message = "Phone number must started with 08 and contain only numeric character!")
    @Size(min = 10, max = 13, message = "Phone number must be 10 - 13 characters length")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;
    @Size(min = 6, max = 16, message = "Password length must be within 6 - 16 character")
    @NotBlank(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$", message = "Password must contain at least one capital letter and one numeric character.")
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
