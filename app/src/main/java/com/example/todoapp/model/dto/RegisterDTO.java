package com.example.todoapp.model.dto;

import com.example.todoapp.model.User;

public class RegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RegisterDTO(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.confirmPassword = user.getPassword();
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = password;
    }
}
