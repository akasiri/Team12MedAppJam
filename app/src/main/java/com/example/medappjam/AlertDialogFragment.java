package com.example.medappjam;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AlertDialog;
import android.util.Log;

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.confirm_delete_message)
                .setTitle(R.string.confirm_delete);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.sharedPreferenceFile), Context.MODE_PRIVATE);
                String username = sharedPref.getString(getString(R.string.user), "");

                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.deletePatient(username);

                //delete info from session
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(getString(R.string.user));
                editor.remove(getString(R.string.isLoggedIn));
                editor.apply();
                getActivity().finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //nothing is done
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
