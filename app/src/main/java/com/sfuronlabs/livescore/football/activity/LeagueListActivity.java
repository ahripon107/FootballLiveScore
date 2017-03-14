package com.sfuronlabs.livescore.football.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.CountryAppStart;
import com.sfuronlabs.livescore.football.model.LeagueAppStart;
import com.sfuronlabs.livescore.football.util.DividerItemDecoration;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.list)
public class LeagueListActivity extends RoboAppCompatActivity{

    @InjectView(R.id.list)
    private RecyclerView leagueList;

    @Inject
    private ArrayList<LeagueAppStart> leagues;

    private CountryAppStart country;

    private BasicListAdapter<LeagueAppStart, LeagueListViewHolder> leaguesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        country = (CountryAppStart) getIntent().getSerializableExtra("country");

        leaguesListAdapter = new BasicListAdapter<LeagueAppStart, LeagueListViewHolder>(leagues) {
            @Override
            public LeagueListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_country, parent, false);
                return new LeagueListViewHolder(view);
            }

            @Override
            public void onBindViewHolder(LeagueListViewHolder holder, final int position) {
                holder.countryName.setText(leagues.get(position).getLeagueName());
                Picasso.with(LeagueListActivity.this).load("http://static.holoduke.nl/footapi/images/flags/"+prepareCountryName(country.getCountry())+".png").into(holder.countryImage);

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LeagueListActivity.this, LeagueDetailsActivity.class);
                        intent.putExtra("leagueKey", leagues.get(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };

        leagueList.setAdapter(leaguesListAdapter);
        leagueList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        leagueList.addItemDecoration(itemDecoration);

        leagues.addAll(country.getLeagues());
        leaguesListAdapter.notifyDataSetChanged();
    }

    private static class LeagueListViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected ImageView countryImage;
        protected TextView countryName;

        public LeagueListViewHolder(View itemView) {
            super(itemView);
            countryImage = ViewHolder.get(itemView, R.id.img_team);
            countryName = ViewHolder.get(itemView, R.id.tv_team_name);
            linearLayout = ViewHolder.get(itemView, R.id.country_layout);
        }
    }

    private String prepareCountryName(String str) {
        return str.replace(' ', '-').toLowerCase();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
