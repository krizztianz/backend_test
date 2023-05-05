package com.kristianandi.BackendTest.Core.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    @Size(min = 1, max = 60, message = "Name must not exceed 60 characters length")
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Column(name = "phone_number", length = 13, nullable = false)
    @Pattern(regexp = "^08[0-9]+", message = "Phone number must started with 08 and contain only numeric character!")
    @Size(min = 10, max = 13, message = "Phone number must be 10 - 13 characters length")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;
    @Column(name = "password", length = 512, nullable = false)
    @Size(min = 6, max = 16, message = "Password length must be within 6 - 16 character")
    @NotBlank(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$", message = "Password must contain at least one capital letter and one numeric character.")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
