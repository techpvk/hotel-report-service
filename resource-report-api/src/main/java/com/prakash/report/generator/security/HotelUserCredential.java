package com.prakash.report.generator.security;

public class HotelUserCredential {

    private String username;
    private String password;

    public HotelUserCredential() {
    }

    public HotelUserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

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