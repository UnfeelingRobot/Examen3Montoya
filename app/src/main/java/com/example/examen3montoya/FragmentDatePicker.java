package com.example.examen3montoya;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

public class FragmentDatePicker extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static FragmentDatePicker newInstance(DatePickerDialog.OnDateSetListener listener) {
        FragmentDatePicker fragment = new FragmentDatePicker();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, listener, year, month, day);
    }
}
