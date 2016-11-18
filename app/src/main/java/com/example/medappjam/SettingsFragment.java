package com.example.medappjam;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;


public class SettingsFragment extends PreferenceFragment {
    private Preference mPreferenceDeleteAccount;
    private Preference mPreferenceChangePassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        mPreferenceDeleteAccount = (Preference) getPreferenceManager().findPreference("delete_account");
        mPreferenceDeleteAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d("tag1", "inOnPreferenceClick");
                AlertDialogFragment alert_delete_account = new AlertDialogFragment();
                alert_delete_account.show(getActivity().getFragmentManager(), "alert delete");
                return true;
            }
        });
    }
}
