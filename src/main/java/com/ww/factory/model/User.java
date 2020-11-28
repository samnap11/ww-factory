package com.ww.factory.model;

public class User {
    private String username;
    private String email;
    private String hashed_password;

    public User(String username, String email, String hashed_password) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashed_password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashed_password) {
        this.hashed_password = hashed_password;
    }
}
