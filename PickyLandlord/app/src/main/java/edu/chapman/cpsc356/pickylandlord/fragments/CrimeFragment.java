package edu.chapman.cpsc356.pickylandlord.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import org.joda.time.format.DateTimeFormat;

import java.util.UUID;

import edu.chapman.cpsc356.pickylandlord.CrimeCollection;
import edu.chapman.cpsc356.pickylandlord.R;
import edu.chapman.cpsc356.pickylandlord.activities.SettingsActivity;
import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

public class CrimeFragment extends Fragment
{
    public static final String ARG_CRIME_ID = "crime_id";

    private CrimeModel crime;

    private EditText textEditText;
    private Button dateButton;
    private CheckBox solvedCheckBox;

    public CrimeFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Have to do this, only in fragments
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        UUID crimeId = (UUID) this.getArguments().getSerializable(ARG_CRIME_ID);

        this.crime = CrimeCollection.Get().getCrime(crimeId);

        // Inflate view
        View fragmentView = inflater.inflate(R.layout.fragment_crime, container, false);

        this.textEditText = (EditText) fragmentView.findViewById(R.id.et_text);
        this.dateButton = (Button) fragmentView.findViewById(R.id.btn_date);
        this.solvedCheckBox = (CheckBox) fragmentView.findViewById(R.id.cb_solved);

        this.textEditText.setText(this.crime.getText());
        this.textEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String newText = textEditText.getText().toString();
                crime.setText(newText);

                Log.d("TEST", "Changed crime text to " + crime.getText());
                Log.d("TEST", crime.isSolved() ? "Solved" : "Not solved");
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        this.dateButton.setText(this.crime.getDate().toString(DateTimeFormat.longDateTime()));

        this.solvedCheckBox.setChecked(this.crime.isSolved());
        this.solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                crime.setSolved(isChecked);
            }
        });

        return fragmentView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_delete:
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.delete_confirmation)
                        .setMessage(R.string.delete_confirmation_message)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                CrimeCollection.Get().deleteCrime(crime);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .create();
                dialog.show();
                return true;
            case R.id.menu_share:

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

                String signature = prefs.getString(SettingsActivity.PREF_SIGNATURE, "");

                String display = "";

                if (!signature.isEmpty())
                {
                    display += signature + "\n";
                }

                display += String.format("%s on %s (%s)",
                        this.crime.getText(),
                        this.crime.getDate().toString(DateTimeFormat.mediumDate()),
                        (this.crime.isSolved() ? "Solved" : "Unsolved")
                );

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, display);

                shareIntent.setType("text/plain");

                startActivity(shareIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_crime, menu);
    }
}
