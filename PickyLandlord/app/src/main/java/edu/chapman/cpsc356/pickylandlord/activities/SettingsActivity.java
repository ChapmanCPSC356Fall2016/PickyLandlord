package edu.chapman.cpsc356.pickylandlord.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import edu.chapman.cpsc356.pickylandlord.R;

public class SettingsActivity extends AppCompatActivity
{
    public static final String PREF_SIGNATURE = "crime_signature";

    private EditText signatureEditText;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.signatureEditText = (EditText) findViewById(R.id.et_signature);

        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String signature = this.prefs.getString(PREF_SIGNATURE, "");

        this.signatureEditText.setText(signature);

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        String userSignature = this.signatureEditText.getText().toString();

        SharedPreferences.Editor editor = this.prefs.edit();

        editor.putString(PREF_SIGNATURE, userSignature);

        editor.apply();
    }
}
