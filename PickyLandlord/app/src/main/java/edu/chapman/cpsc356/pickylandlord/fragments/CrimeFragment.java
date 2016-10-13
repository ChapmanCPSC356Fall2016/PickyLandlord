package edu.chapman.cpsc356.pickylandlord.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edu.chapman.cpsc356.pickylandlord.R;
import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

public class CrimeFragment extends Fragment
{
    private CrimeModel crime;

    private EditText textEditText;

    public CrimeFragment()
    {
        this.crime = new CrimeModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // Inflate view
        View fragmentView = inflater.inflate(R.layout.fragment_crime, container, false);

        this.textEditText = (EditText) fragmentView.findViewById(R.id.et_text);

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
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        return fragmentView;
    }
}