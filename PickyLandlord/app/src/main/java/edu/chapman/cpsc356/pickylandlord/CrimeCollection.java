package edu.chapman.cpsc356.pickylandlord;

import java.util.ArrayList;
import java.util.List;

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
            this.crimes.add(new CrimeModel());
        }
    }

    public List<CrimeModel> getCrimes()
    {
        return this.crimes;
    }

    //TODO:
    // public CrimeModel getCrime
}
