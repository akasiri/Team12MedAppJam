package com.example.medappjam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/** Multiple choice alert dialog
 *
 */
public class MultipleChoiceAlertDialogFragment extends DialogFragment {
    private ArrayList mSelectedItems;

    //get already selected profiles to have it checked by default (currently set first profile to true for testing purposes)
    private boolean[] mAlreadySelected = {true, false, false};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.select_profiles)
                .setMultiChoiceItems(R.array.profile_array, mAlreadySelected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedItem, boolean isChecked) {
                //do something with the items
            }
        })
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //do something on click confirm
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //do something on click cancel (don't save revised items)
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
