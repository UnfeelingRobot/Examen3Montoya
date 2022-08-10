package com.example.examen3montoya;

public class User {
    private static String name;
    private static String email;
    private static String password;
    private static String number;

    public User() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        User.number = number;
    }

    public static boolean verifyUserExistence(){
        return name != null && email != null && password != null && number != null;
    }
}
