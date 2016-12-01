package edu.chapman.cpsc356.pickylandlord.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.chapman.cpsc356.pickylandlord.R;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_add:
                startActivity(new Intent(this, CrimePagerActivity.class));
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_crime_list, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
