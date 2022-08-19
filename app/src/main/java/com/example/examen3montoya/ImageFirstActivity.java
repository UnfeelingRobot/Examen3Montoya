package com.example.examen3montoya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ImageFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void boton(View v){
        Intent intent = new Intent(ImageFirstActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}