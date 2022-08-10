package com.example.examen3montoya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    TextView textLogIn;
    MaterialButton registerButton;
    TextInputLayout inputLayoutUsername, inputLayoutEmail, inputLayoutPhoneNumber , inputLayoutPassword;
    TextInputEditText inputUsername, inputEmail,inputPhone,inputPassword;
    String textUsername, textEmail, textPhone, textPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textLogIn = findViewById(R.id.textLogIn);
        inputLayoutUsername = findViewById(R.id.username);
        inputLayoutEmail = findViewById(R.id.email);
        inputLayoutPhoneNumber = findViewById(R.id.number);
        inputLayoutPassword = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);
        inputUsername=findViewById(R.id.input_user);
        inputEmail=findViewById(R.id.input_email);
        inputPhone=findViewById(R.id.input_number);
        inputPassword=findViewById(R.id.input_password);

        textLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Functions.verifyCompleteData(inputUsername, inputEmail, inputPassword)){
                    Functions.showAlert(RegisterActivity.this, "Error in sign in", "You have the enter complete data");

                } else if (!inputEmail.getText().toString().endsWith("@gmail.com") && (!inputEmail.getText().toString().endsWith("@hotmail.com"))){
                        Functions.showAlert(RegisterActivity.this, "Error in sign in", "The email entered is invalid");

                }else{
                    User.setName(inputUsername.getText().toString());
                    User.setEmail(inputEmail.getText().toString());
                    User.setPassword(inputPassword.getText().toString());
                    Snackbar sb = Snackbar.make(view, "Registered user successfully. Now log in.",
                            Snackbar.LENGTH_LONG);
                    sb.setDuration(5000);
                    sb.show();
                }
            }
        });
    }
}