package edu.chapman.cpsc356.pickylandlord.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import edu.chapman.cpsc356.pickylandlord.CrimeCollection;
import edu.chapman.cpsc356.pickylandlord.R;
import edu.chapman.cpsc356.pickylandlord.fragments.CrimeFragment;
import edu.chapman.cpsc356.pickylandlord.fragments.CrimeListFragment;
import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

public class CrimePagerActivity extends AppCompatActivity
{
    private ViewPager crimePager;
    private List<CrimeModel> crimes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID startingCrimeId;

        if (getIntent().hasExtra(CrimeListFragment.EXTRA_CRIME_ID))
        {
            startingCrimeId = (UUID) getIntent().getSerializableExtra(CrimeListFragment.EXTRA_CRIME_ID);
        }
        else
        {
            // Create new crime
            CrimeModel newCrime = new CrimeModel();

            // add to collection
            CrimeCollection.Get().addCrime(newCrime);

            // start fragment with new crime id
            startingCrimeId = newCrime.getId();
        }

        this.crimes = CrimeCollection.Get().getCrimes();

        CrimeModel startingCrime = CrimeCollection.Get().getCrime(startingCrimeId);
        int startingIndex = crimes.indexOf(startingCrime);

        this.crimePager = (ViewPager) findViewById(R.id.vp_crimes);
        this.crimePager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                CrimeModel crime = crimes.get(position);

                CrimeFragment frag = new CrimeFragment();

                Bundle argBundle = new Bundle();
                argBundle.putSerializable(CrimeFragment.ARG_CRIME_ID, crime.getId());

                frag.setArguments(argBundle);

                return frag;
            }

            @Override
            public int getCount()
            {
                return crimes.size();
            }
        });

        this.crimePager.setCurrentItem(startingIndex);
    }
}
