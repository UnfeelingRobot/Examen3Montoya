package com.example.examen3montoya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView textRegister;
    MaterialButton loginButton;
    TextInputEditText inputUser,inputPassword;
    TextInputLayout inputLayoutUser, inputLayoutPassword;
    String textUser, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textRegister = findViewById(R.id.textRegister);
        loginButton = findViewById(R.id.login_button);
        inputLayoutUser = findViewById(R.id.email);
        inputLayoutPassword = findViewById(R.id.password);
        inputUser=findViewById(R.id.input_user);
        inputPassword=findViewById(R.id.input_password);

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Functions.verifyCompleteData(inputUser, inputPassword)) {
                    Functions.showAlert(LoginActivity.this, "Error in log in", "You have the enter complete data");
                }
               else if (!User.verifyUserExistence()){
                    Functions.showAlert(LoginActivity.this, "Error in log in", "You have not registered");
                }
                else if (User.getName().equals(inputUser.getText().toString()) && User.getPassword().equals(inputPassword.getText().toString())){
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Functions.showAlert(LoginActivity.this, "Error in log in", "The data is incorrect");

                }
            }
        });
    }

}