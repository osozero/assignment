package com.shopinle.assignment.dto.login;

import com.shopinle.assignment.model.User;

public class UserLoginResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String status;
    private String error;

    private String token;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static UserLoginResponseDto buildFrom(User user){
        UserLoginResponseDto response = new UserLoginResponseDto();

        response.setStatus("ok");
        response.setError("");

        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());

        return response;
    }
}
