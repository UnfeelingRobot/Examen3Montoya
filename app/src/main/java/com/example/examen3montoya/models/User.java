package com.example.examen3montoya.models;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String day_birth;
    private String phone;

    public User() {
    }

    public User(String email, String password, String name, String surname, String day_birth, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.day_birth = day_birth;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDay_birth() {
        return day_birth;
    }

    public void setDay_birth(String day_birth) {
        this.day_birth = day_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
