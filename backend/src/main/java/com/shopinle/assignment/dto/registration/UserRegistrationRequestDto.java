package com.shopinle.assignment.dto.registration;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationRequestDto {

    @NotNull(message = "can't be null")
    @Size(min = 3,message = "minimum username length is 3")
    private String username;

    @NotNull(message = "can't be null")
    private String email;

    @NotNull(message = "can't be null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
