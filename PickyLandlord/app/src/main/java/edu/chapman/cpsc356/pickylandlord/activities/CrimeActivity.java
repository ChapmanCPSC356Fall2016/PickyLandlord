package edu.chapman.cpsc356.pickylandlord.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

import edu.chapman.cpsc356.pickylandlord.CrimeCollection;
import edu.chapman.cpsc356.pickylandlord.fragments.CrimeFragment;
import edu.chapman.cpsc356.pickylandlord.fragments.CrimeListFragment;
import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

/**
 * Created by ryanburns on 10/31/16.
 */

public class CrimeActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment getFragment()
    {
        UUID crimeId;

        if (getIntent().hasExtra(CrimeListFragment.EXTRA_CRIME_ID))
        {
            crimeId = (UUID) getIntent().getSerializableExtra(CrimeListFragment.EXTRA_CRIME_ID);
        }
        else
        {
            // Create new crime
            CrimeModel newCrime = new CrimeModel();

            // add to collection
            CrimeCollection.Get().addCrime(newCrime);

            // start fragment with new crime id
            crimeId = newCrime.getId();
        }

        CrimeFragment frag = new CrimeFragment();

        Bundle argBundle = new Bundle();
        argBundle.putSerializable(CrimeFragment.ARG_CRIME_ID, crimeId);

        frag.setArguments(argBundle);

        return frag;
    }
}
