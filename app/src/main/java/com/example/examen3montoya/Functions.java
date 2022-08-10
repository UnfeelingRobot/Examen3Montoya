package com.example.examen3montoya;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class Functions {

    public static boolean verifyCompleteData(TextInputEditText... inputs){
        for (TextInputEditText i : inputs){
            //Log.i("Test resultado", TextUtils.isEmpty(i.getText().toString().trim()) + "");
            if (TextUtils.isEmpty(i.getText().toString().trim())){
                return false;
            }
        }
        return true;
    }

    public static void showAlert(Context context, String title, String description){
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
