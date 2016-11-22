package com.example.medappjam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class PromptForProviderFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.offer_add_provider_message)
                .setTitle(R.string.offer_add_provider);

        builder.setPositiveButton(R.string.offer_add_provider_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getActivity(), AddProviderActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
        builder.setNegativeButton(R.string.offer_add_provider_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
