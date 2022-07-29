package com.example.examen3montoya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    TextView textLogIn;
    MaterialButton registerButton;
    TextInputLayout inputLayoutFullName, inputLayoutNickname, inputLayoutPhoneNumber , inputLayoutPassword;
    TextInputEditText inputFullName, inputNickname,inputPhone,inputPassword;
    String textFullName, textNickname, textPhone, textPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textLogIn = findViewById(R.id.textLogIn);
        inputLayoutFullName = findViewById(R.id.fullname);
        inputLayoutNickname = findViewById(R.id.email);
        inputLayoutPhoneNumber = findViewById(R.id.number);
        inputLayoutPassword = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);
        inputFullName=findViewById(R.id.textfullname);
        inputNickname=findViewById(R.id.textemail);
        inputPhone=findViewById(R.id.textnumber);
        inputPassword=findViewById(R.id.textpassword);

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
                textFullName=inputFullName.getText().toString().trim();
                textNickname=inputNickname.getText().toString().trim();
                textPhone=inputPhone.getText().toString().trim();
                textPassword=inputPassword.getText().toString().trim();

                if(TextUtils.isEmpty(textFullName) || TextUtils.isEmpty(textNickname) || TextUtils.isEmpty(textPhone) ||  TextUtils.isEmpty(textPassword) ){
                    if(TextUtils.isEmpty(textFullName)) {
                        inputLayoutFullName.setError("Debe llenar el campo con su nombre");
                    } else {
                        inputLayoutFullName.setError(null);
                    }
                    if(TextUtils.isEmpty(textNickname)) {
                        inputLayoutNickname.setError("Debe llenar el campo con su email");
                    }
                    else {
                        inputLayoutNickname.setError(null);
                    }
                    if(TextUtils.isEmpty(textPhone)) {
                        inputLayoutPhoneNumber.setError("Debe llenar el campo con su número");
                    }
                    else {
                        inputLayoutPhoneNumber.setError(null);
                    }
                    if(TextUtils.isEmpty(textPassword)) {
                        inputLayoutPassword.setError("Debe llenar el campo con su contraseña");
                    }
                    else {
                        inputLayoutPassword.setError(null);
                    }
                } else {


                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
//
            }
        });
    }
}