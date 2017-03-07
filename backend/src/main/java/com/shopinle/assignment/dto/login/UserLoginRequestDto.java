package com.shopinle.assignment.dto.login;

import javax.validation.constraints.NotNull;

public class UserLoginRequestDto {
    @NotNull(message = "can't be null")
    private String username;

    @NotNull(message = "can't be null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
