package edu.chapman.cpsc356.pickylandlord.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.chapman.cpsc356.pickylandlord.R;

public abstract class SingleFragmentActivity extends AppCompatActivity
{

    protected abstract Fragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        // 1. get the fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // 2. Create instance of fragment
        Fragment fragment = getFragment();

        // 3. Use a fragment transaction to show fragment
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_fragment_container, fragment);
        transaction.commit();
    }
}
