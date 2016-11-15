package edu.chapman.cpsc356.pickylandlord;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

/**
 * Created by ryanburns on 10/24/16.
 * Singleton CrimeCollection class
 */

public class CrimeCollection
{
    private static CrimeCollection collection;

    private List<CrimeModel> crimes;

    public static CrimeCollection Get()
    {
        if (collection == null)
        {
            collection = new CrimeCollection();
        }

        return collection;
    }

    private CrimeCollection()
    {
        this.crimes = new ArrayList<>();

        // for demonstration
        for (int i = 0; i < 100; ++i)
        {
            CrimeModel crime = new CrimeModel();
            crime.setText("Crime #" + (i + 1));
            crime.setSolved(i % 2 == 0);

            this.crimes.add(crime);
        }
    }

    public List<CrimeModel> getCrimes()
    {
        return this.crimes;
    }

    public CrimeModel getCrime(UUID id)
    {
        for (CrimeModel crime : this.crimes)
        {
            if (crime.getId().equals(id))
            {
                return crime;
            }
        }

        return null;
    }

    public void addCrime(CrimeModel crime)
    {
        this.crimes.add(crime);
    }

    public void deleteCrime(CrimeModel crime)
    {
        this.crimes.remove(crime);
    }
}
