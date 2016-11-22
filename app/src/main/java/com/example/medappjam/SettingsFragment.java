package com.example.medappjam;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;


public class SettingsFragment extends PreferenceFragment {
    private Preference mPreferenceDeleteAccount;
    private Preference mPreferenceChangePassword;
    private Preference mPreferenceEditProfiles;
    private Preference mPreferenceEditProviderInfo;
    private Preference mPreferenceDeleteRecords;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        mPreferenceDeleteAccount = (Preference) getPreferenceManager().findPreference("delete_account");
        mPreferenceDeleteAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialogFragment alertDeleteAccount = new AlertDialogFragment();
                alertDeleteAccount.show(getActivity().getFragmentManager(), "alert delete");
                return true;
            }
        });

        mPreferenceChangePassword = (Preference) getPreferenceManager().findPreference("change_password");
        mPreferenceChangePassword.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //start change password activity
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
                return true;
            }
        });

        mPreferenceEditProfiles = (Preference) getPreferenceManager().findPreference("edit_profiles");
        mPreferenceEditProfiles.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                MultipleChoiceAlertDialogFragment profileChoices = new MultipleChoiceAlertDialogFragment();
                profileChoices.show(getActivity().getFragmentManager(), "profiles");
                return true;
            }
        });

        mPreferenceEditProviderInfo = (Preference) getPreferenceManager().findPreference("edit_provider_info");
        mPreferenceEditProviderInfo.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), EditProviderInfoActivity.class);
                startActivity(intent);
                return true;
            }
        });
        mPreferenceDeleteRecords = (Preference) getPreferenceManager().findPreference("delete_records");
        mPreferenceDeleteRecords.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), DeleteRecordsActivity.class);
                startActivity(intent);
                return true;
            }
        });


    }
}
