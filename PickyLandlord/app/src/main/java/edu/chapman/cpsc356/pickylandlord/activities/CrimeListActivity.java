package edu.chapman.cpsc356.pickylandlord.activities;

import android.support.v4.app.Fragment;

import edu.chapman.cpsc356.pickylandlord.fragments.CrimeListFragment;

/**
 * Created by ryanburns on 11/2/16.
 */

public class CrimeListActivity extends SingleFragmentActivity
{

    @Override
    protected Fragment getFragment()
    {
        return new CrimeListFragment();
    }
}
