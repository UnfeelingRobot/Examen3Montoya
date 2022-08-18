package com.example.examen3montoya.models;

import java.io.Serializable;

public class Date implements Serializable {
    private String email;
    private String type;
    private String day;
    private String day_diary;

    public Date(String email, String type, String day, String day_diary) {
        this.email = email;
        this.type = type;
        this.day = day;
        this.day_diary = day_diary;
    }
    public Date(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay_diary() {
        return day_diary;
    }

    public void setDay_diary(String day_diary) {
        this.day_diary = day_diary;
    }
}
