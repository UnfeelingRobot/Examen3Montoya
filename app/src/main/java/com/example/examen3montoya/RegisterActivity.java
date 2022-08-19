package com.example.examen3montoya;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.examen3montoya.db.Connection;
import com.example.examen3montoya.table.Tables;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    // Campos
    public EditText editTextFirstName, editTextLastName, editTextDOB, editTextEmail, editTextPassword1, editTextPassword2, editTextPhone;
    private FirebaseAuth auth;
    // Objeto para realizar validaciones
    AwesomeValidation awesomeValidation;

    @RequiresApi(api = Build.VERSION_CODES.N)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        // Obteniendo los campos
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        editTextPhone = findViewById(R.id.editTextPhone);

        addValidations();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addValidations() {
        // Variable que aplicará las validaciones
        awesomeValidation = new AwesomeValidation(ValidationStyle.UNDERLABEL);
        awesomeValidation.setContext(this);
        awesomeValidation.setUnderlabelColor(ContextCompat.getColor(this, android.R.color.holo_red_light));

        // Validación nombre y apellido
        awesomeValidation.addValidation(this, R.id.editTextFirstName, "[A-zÀ-ú]+", R.string.error_name);
        awesomeValidation.addValidation(this, R.id.editTextLastName, "[A-zÀ-ú]+" , R.string.error_name);
        // Validación correo
        awesomeValidation.addValidation(this, R.id.editTextEmailAddress, Patterns.EMAIL_ADDRESS, R.string.error_email);
        // Validación fecha de nacimiento
        awesomeValidation.addValidation(this, R.id.editTextDOB, input -> {
            // check if the age is >= 16
            try {
                Calendar calendarBirthday = Calendar.getInstance();
                Calendar calendarToday = Calendar.getInstance();
                calendarBirthday.setTime(new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(input));
                int yearOfToday = calendarToday.get(Calendar.YEAR);
                int yearOfBirthday = calendarBirthday.get(Calendar.YEAR);
                if (yearOfToday - yearOfBirthday > 16) {
                    return true;
                } else if (yearOfToday - yearOfBirthday == 16) {
                    int monthOfToday = calendarToday.get(Calendar.MONTH);
                    int monthOfBirthday = calendarBirthday.get(Calendar.MONTH);
                    if (monthOfToday > monthOfBirthday) {
                        return true;
                    } else if (monthOfToday == monthOfBirthday) {
                        if (calendarToday.get(Calendar.DAY_OF_MONTH) >= calendarBirthday.get(Calendar.DAY_OF_MONTH)) {
                            return true;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }, R.string.error_year);
        // Validación contraseña
        awesomeValidation.addValidation(this, R.id.editTextPassword1, ".{6,}", R.string.error_password);
        awesomeValidation.addValidation(this, R.id.editTextPassword2, R.id.editTextPassword1, R.string.wrong_password);
        // Validación teléfono
        awesomeValidation.addValidation(this, R.id.editTextPhone, "\\d{10}", R.string.error_phone);
    }

    // Métodos públicos
    public void buttonSignUp(View view) {
        if (awesomeValidation.validate()) {

            try {
                long resultado = insertUser();
                if (resultado != -1) {
                    createuser();

                } else {
                    Toast.makeText(this, "E\n" + "Email is already registered", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, e + "", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Check the fields", Toast.LENGTH_SHORT).show();
        }
    }

    private long insertUser() {


        Connection conn = new Connection(this,"bd_users",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Tables.FIELD_ID_EMAIL, editTextEmail.getText().toString());
        values.put(Tables.FIELD_PASSWORD, editTextPassword1.getText().toString());
        values.put(Tables.FIELD_NAME, editTextFirstName.getText().toString());
        values.put(Tables.FIELD_SURNAME, editTextLastName.getText().toString());
        values.put(Tables.FIELD_DATE_OF_BIRTH, editTextDOB.getText().toString());
        values.put(Tables.FIELD_PHONE, editTextPhone.getText().toString());

        long id = db.insert(Tables.TABLE_USER, Tables.FIELD_ID_EMAIL, values);

        db.close();

        return id;
    }

    public void showDatePickerDialog(View v) {
        FragmentDatePicker newFragment = FragmentDatePicker.newInstance((datePicker, year, month, day) -> {

            final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;
            editTextDOB.setText(selectedDate);
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    private void createuser(){

        auth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            createCheckPass();


                        } else {
                            // If sign in fails, display a message to the user.
                            createCheckError();
                        }
                    }
                });


    }
    public void createCheckPass(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        Toast.makeText(this, "\n" + "Registered user!", Toast.LENGTH_SHORT).show();
    }
    public void createCheckError(){
        Toast.makeText(this, "\n" + "Hubo un problema al registrar el usuario!", Toast.LENGTH_SHORT).show();
    }
}