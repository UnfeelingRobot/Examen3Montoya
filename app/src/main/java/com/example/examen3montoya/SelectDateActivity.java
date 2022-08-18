package com.example.examen3montoya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen3montoya.db.Connection;
import com.example.examen3montoya.models.Date;
import com.example.examen3montoya.table.Tables;

import static com.example.examen3montoya.R.layout.custom_spinner;

import java.util.ArrayList;

public class SelectDateActivity extends AppCompatActivity {

    public TextView textViewTitle, textViewEmpty, editTextSessionType, editTextDateTime, editTextDateScheduled, textView, textView2, textView3;
    public Spinner spinnerSelectDate;
    public Button buttonCancelDate;

    Connection conn;

    ArrayList<String> listDates;
    ArrayList<Date> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        conn = new Connection(getApplicationContext(),"bd_users",null,1);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        spinnerSelectDate = findViewById(R.id.spinnerSelectDate);
        buttonCancelDate = findViewById(R.id.buttonCancelDate);
        editTextSessionType = findViewById(R.id.editTextSessionType);
        editTextDateTime = findViewById(R.id.editTextDateTime);
        editTextDateScheduled = findViewById(R.id.editTextDateScheduled);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        buttonCancelDate.setEnabled(false);

        if (checkDates()) {
            fillDates();

            ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                    this,
                    custom_spinner,
                    listDates
            );

            adaptador.setDropDownViewResource(R.layout.custom_spinner_dropdown);
            spinnerSelectDate.setAdapter(adaptador);

            spinnerSelectDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                    if (position != 0) {
                        buttonCancelDate.setEnabled(true);

                        textView.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        buttonCancelDate.setVisibility(View.VISIBLE);
                        editTextSessionType.setVisibility(View.VISIBLE);
                        editTextDateTime.setVisibility(View.VISIBLE);
                        editTextDateScheduled.setVisibility(View.VISIBLE);
                        buttonCancelDate.setVisibility(View.VISIBLE);

                        editTextSessionType.setText(dateList.get(position - 1).getType());
                        editTextDateTime.setText(dateList.get(position - 1).getDay());
                        editTextDateScheduled.setText(dateList.get(position - 1).getDay_diary() + " hrs");
                    } else {
                        buttonCancelDate.setEnabled(false);

                        textView.setVisibility(View.INVISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        textView3.setVisibility(View.INVISIBLE);
                        buttonCancelDate.setVisibility(View.INVISIBLE);
                        editTextSessionType.setVisibility(View.INVISIBLE);
                        editTextDateTime.setVisibility(View.INVISIBLE);
                        editTextDateScheduled.setVisibility(View.INVISIBLE);
                        buttonCancelDate.setVisibility(View.INVISIBLE);

                        editTextSessionType.setText("");
                        editTextDateTime.setText("");
                        editTextDateScheduled.setText("");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } else {
            textViewEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void fillDates() {
        textViewTitle.setVisibility(View.VISIBLE);
        spinnerSelectDate.setVisibility(View.VISIBLE);

        spinnerSelectDate = findViewById(R.id.spinnerSelectDate);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                custom_spinner,
                listDates
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerSelectDate.setAdapter(adapter);
    }

    private boolean checkDates() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Date date;
        dateList = new ArrayList<>();

        SharedPreferences sharedPref = this.getSharedPreferences("email", Context.MODE_PRIVATE);
        String email = sharedPref.getString(getString(R.string.email), "");

        String[] parametros = { email };
        String[] campos = { Tables.FIELD_TYPE, Tables.FIELD_DAY_DATE, Tables.FIELD_DAY_DIARY };

        try {
            // Select correo electrónico from usuario where correo electrónico =?
            Cursor cursor = db.query(Tables.TABLE_DATES,
                    campos,
                    Tables.FIELD_ID_EMAIL + " = ? ",
                    parametros,
                    null,
                    null,
                    Tables.FIELD_DAY_DATE);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()){
                    date = new Date();
                    date.setType(cursor.getString(0));
                    date.setDay(cursor.getString(1));
                    date.setDay_diary(cursor.getString(2));

                    Log.i("Type", date.getType());
                    Log.i("Day", date.getDay());

                    dateList.add(date);

                    getList();

                }
                cursor.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void getList() {
        listDates = new ArrayList<>();
        listDates.add("<Select a day>");

        for(int i=0; i < dateList.size(); i++){
            listDates.add(dateList.get(i).getType() + " - " + dateList.get(i).getDay());
        }
    }

    public void eliminarUsuario(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog);
        builder.setTitle(R.string.are_sure);
        builder.setMessage(R.string.delete);
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {

        });
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            // Hacer cosas aqui al hacer clic en el boton de aceptar
            try {
                SQLiteDatabase db = conn.getWritableDatabase();
                String[] parametros = { editTextDateTime.getText().toString() };

                db.delete(Tables.TABLE_DATES, Tables.FIELD_DAY_DATE + "=?", parametros);
                Toast.makeText(getApplicationContext(),"\n" +
                        "The appointment was canceled", Toast.LENGTH_LONG).show();

                spinnerSelectDate.setSelection(0);

                Intent intent = getIntent();
                finish();
                startActivity(intent);

                db.close();
            } catch (Exception e) {
                Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }
}