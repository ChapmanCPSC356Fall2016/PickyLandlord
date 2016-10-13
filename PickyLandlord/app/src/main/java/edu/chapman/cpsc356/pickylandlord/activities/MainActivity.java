package edu.chapman.cpsc356.pickylandlord.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.chapman.cpsc356.pickylandlord.R;
import edu.chapman.cpsc356.pickylandlord.fragments.CrimeFragment;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. get the fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // 2. Create instance of fragment
        CrimeFragment crimeFragment = new CrimeFragment();

        // 3. Use a fragment transaction to show fragment
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_fragment_container, crimeFragment);
        transaction.commit();
    }
}
