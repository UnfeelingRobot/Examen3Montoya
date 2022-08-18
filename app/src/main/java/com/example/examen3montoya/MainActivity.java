package com.example.examen3montoya;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textViewName;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences("name", Context.MODE_PRIVATE);
        String name = sharedPref.getString(getString(R.string.first_name), "");

        textViewName = findViewById(R.id.textViewName);

        textViewName.setText("Hello " + name);
    }

    // Métodos públicos
    // Método que dirige a agendar cita
    public void buttonPlanDate(View view) {
        Intent intent = new Intent(this, ScheduleDateActivity.class);
        startActivity(intent);
    }

    // Método que dirige a cancelar cita
    public void buttonCancelDate(View view) {
        Intent intent = new Intent(this, SelectDateActivity.class);
        startActivity(intent);
    }

    // Método que dirige a salir de la sesión
    public void buttonExit(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences("email", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // Método que dirige a la información del salón
    public void buttonAboutMe(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }
}