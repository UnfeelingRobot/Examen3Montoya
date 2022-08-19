package com.example.examen3montoya;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Campos
    public EditText editTextEmail, editTextPassword;

    AwesomeValidation awesomeValidation;
    Connection conn;
    SQLiteDatabase db;
    private FirebaseAuth auth;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        // Obteniendo los campos
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        awesomeValidation = new AwesomeValidation(ValidationStyle.UNDERLABEL);
        awesomeValidation.setContext(this);
        awesomeValidation.setUnderlabelColor(ContextCompat.getColor(this, android.R.color.holo_red_light));

        // Validación correo
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.error_email);

        // Validación contraseña
        awesomeValidation.addValidation(this, R.id.editTextPassword, ".{6,}", R.string.error_password);

        conn = new Connection(getApplicationContext(),"bd_users",null,1);
    }

    // Métodos públicos
    // Método que valida el formulario
    public void buttonLogin(View view) {
        String email, password;
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();


        if (awesomeValidation.validate()) if (checkEmail(email)) {
            if (checkEmailPassword(email, password)) {

                SharedPreferences sharedPref = this.getSharedPreferences("email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.email), email);
                editor.apply();
                signIn();

            }
        }
    }

    public void buttonSignUp(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public Boolean checkEmail(String email) {

        db = conn.getReadableDatabase();
        String[] parameters = { email };
        String[] fields = {Tables.FIELD_NAME };

        try {
            // Select correo electrónico from usuario where correo electrónico =?
            Cursor cursor = db.query(Tables.TABLE_USER,
                    fields,
                    Tables.FIELD_ID_EMAIL + " = ? ",
                    parameters,
                    null,
                    null,
                    null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                SharedPreferences sharedPref = this.getSharedPreferences("name", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.first_name), cursor.getString(0));
                editor.apply();

                cursor.close();
                return true;
            } else {

                editTextPassword.setText("");

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog);
                builder.setTitle(R.string.email_not_found);
                builder.setMessage(email + " Does not match any existing account. You can create an account to access");
                builder.setNegativeButton(R.string.try_again, (dialog, which) -> {

                });
                builder.setPositiveButton(R.string.sign_up, (dialog, which) -> {
                    // Hacer cosas aqui al hacer clic en el boton de aceptar
                    Intent intent = new Intent(this, RegisterActivity.class);
                    startActivity(intent);
                });
                builder.show();
            }
        } catch (Exception e) {

            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = conn.getReadableDatabase();

        try {

            // Select correo electrónico from usuario where correo electrónico =?
            Cursor cursor = db.rawQuery("Select " + Tables.FIELD_ID_EMAIL + ", " + Tables.FIELD_PASSWORD +
                            " from " + Tables.TABLE_USER
                            + " where " + Tables.FIELD_ID_EMAIL + " = ?  and " + Tables.FIELD_PASSWORD + "= ?",
                    new String[] { email, password });

            if (cursor.getCount() > 0) {

                return true;

            } else {

                editTextPassword.setText("");
                cursor.close();

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog);
                builder.setTitle(R.string.email_not_match);
                builder.setMessage("The password does not match the registered account");
                builder.setNegativeButton(R.string.try_again, (dialog, which) -> {

                });
                builder.show();
            }
        } catch (Exception e) {

            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    protected void onStart() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        super.onStart();
    }


    public void signIn(){
        String email, password;
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("sdsd");

                            signInCheckPass();
                        } else {
                            // If sign in fails, display a message to the user.

                            signInCheckError();
                        }
                    }
                });
    }
    public void signInCheckPass(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void signInCheckError(){
        Toast.makeText(this, "\n" + "Hubo un problema al registrarse!", Toast.LENGTH_SHORT).show();

    }

}

