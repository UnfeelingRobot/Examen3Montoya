package com.example.examen3montoya.table;

public class Tables {

    //Constantes campos tabla usuario
    public static final String TABLE_USER = "user";
    public static final String FIELD_ID_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_SURNAME = "surname";
    public static final String FIELD_DATE_OF_BIRTH = "date_of_birth";
    public static final String FIELD_PHONE = "phone";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            FIELD_ID_EMAIL + " TEXT UNIQUE PRIMARY KEY, " +
            FIELD_PASSWORD + " TEXT, " +
            FIELD_NAME + " TEXT, " +
            FIELD_SURNAME + " TEXT, " +
            FIELD_DATE_OF_BIRTH + " TEXT, " +
            FIELD_PHONE + " TEXT)";

    // Constantes campos tabla citas
    public static final String TABLE_DATES = "dates";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_PSICO = "psico";
    public static final String FIELD_DAY_DATE = "day_date";
    public static final String FIELD_DAY_DIARY = "day_diary";

    public static final String CREATE_TABLE_DATES = "CREATE TABLE " + TABLE_DATES + " (" +
            FIELD_ID_EMAIL + " TEXT, " +
            FIELD_TYPE + " TEXT, " +
            FIELD_PSICO + " TEXT, " +
            FIELD_DAY_DATE + " TEXT UNIQUE, " +
            FIELD_DAY_DIARY + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            " FOREIGN KEY (" + FIELD_ID_EMAIL + ") REFERENCES " + TABLE_USER + "(" + FIELD_ID_EMAIL + "));";
}
