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
    TextInputEditText inputNickname,inputPassword;
    TextInputLayout inputLayoutNickname, inputLayoutPassword;
    String textNickname, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textRegister = findViewById(R.id.textRegister);
        loginButton = findViewById(R.id.login_button);
        inputLayoutNickname = findViewById(R.id.email);
        inputLayoutPassword = findViewById(R.id.password);
        inputNickname=findViewById(R.id.textemail);
        inputPassword=findViewById(R.id.textpassword);

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
                textNickname=inputNickname.getText().toString();
                textPassword=inputPassword.getText().toString();

                if(TextUtils.isEmpty(textNickname) || TextUtils.isEmpty(textPassword)) {
                    if (TextUtils.isEmpty(textNickname)) {
                        inputLayoutNickname.setError("You must fill in the field with your email");
                    } else {
                        inputLayoutNickname.setError(null);
                    }
                    if (TextUtils.isEmpty(textPassword)) {
                        inputLayoutPassword.setError("You must fill in the field with your password");
                    } else {
                        inputLayoutPassword.setError(null);
                    }
                }else {

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

}