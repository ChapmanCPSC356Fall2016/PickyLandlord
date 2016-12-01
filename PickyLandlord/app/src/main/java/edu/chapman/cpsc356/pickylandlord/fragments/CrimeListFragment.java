package edu.chapman.cpsc356.pickylandlord.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.chapman.cpsc356.pickylandlord.CrimeCollection;
import edu.chapman.cpsc356.pickylandlord.R;
import edu.chapman.cpsc356.pickylandlord.activities.CrimePagerActivity;
import edu.chapman.cpsc356.pickylandlord.models.CrimeModel;

/**
 * Created by ryanburns on 11/2/16.
 */

public class CrimeListFragment extends Fragment
{
    public static final String EXTRA_CRIME_ID = "crime_id";

    private RecyclerView crimesListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        this.crimesListView = (RecyclerView) view.findViewById(R.id.rv_crimes);

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (this.crimesListView.getAdapter() == null)
        {
            CrimeAdapter crimeAdapter = new CrimeAdapter(CrimeCollection.Get().getCrimes());

            this.crimesListView.setAdapter(crimeAdapter);
            this.crimesListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        else
        {
            this.crimesListView.getAdapter().notifyDataSetChanged();
        }

    }

    private class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView titleTextView;
        private CheckBox solvedCheckBox;

        private CrimeModel crime;

        public CrimeViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            this.titleTextView = (TextView) itemView.findViewById(R.id.tv_item_title);
            this.solvedCheckBox = (CheckBox) itemView.findViewById(R.id.cb_item_solved);
        }

        public void populate(CrimeModel crime)
        {
            this.crime = crime;

            this.titleTextView.setText(crime.getText());
            this.solvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View view)
        {
            Intent crimeIntent = new Intent(getActivity(), CrimePagerActivity.class);
            crimeIntent.putExtra(EXTRA_CRIME_ID, this.crime.getId());

            startActivity(crimeIntent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeViewHolder>
    {
        private List<CrimeModel> crimes;

        public CrimeAdapter(List<CrimeModel> crimes)
        {
            this.crimes = crimes;
        }

        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View holderView = inflater.inflate(R.layout.item_crime, parent, false);

            return new CrimeViewHolder(holderView);
        }

        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position)
        {
            CrimeModel item = this.crimes.get(position);
            holder.populate(item);

            Log.d("TEST", "onBind on position " + position);
        }

        @Override
        public int getItemCount()
        {
            return this.crimes.size();
        }
    }
}
