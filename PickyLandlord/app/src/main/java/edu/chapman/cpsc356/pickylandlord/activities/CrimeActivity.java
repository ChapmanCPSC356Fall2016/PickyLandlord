package edu.chapman.cpsc356.pickylandlord.activities;

import android.support.v4.app.Fragment;

import edu.chapman.cpsc356.pickylandlord.fragments.CrimeFragment;

/**
 * Created by ryanburns on 10/31/16.
 */

public class CrimeActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment getFragment()
    {
        return new CrimeFragment();
    }
}
