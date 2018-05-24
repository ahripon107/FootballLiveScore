package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.LeagueScheduleFragment;
import com.sfuronlabs.livescore.football.fragments.StandingFragment;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_league_details)
public class LeagueDetailsActivity extends RoboAppCompatActivity {

    private String leagueKey;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_schedule:
                    return setupFragment(getScheduleFragment());
                case R.id.navigation_standings:
                    return setupFragment(getStandingFragment());
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("leaguename"));

        leagueKey = getIntent().getStringExtra("leagueKey");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setupFragment(getScheduleFragment());
    }

    private boolean setupFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        return true;
    }

    private Fragment getScheduleFragment() {
        LeagueScheduleFragment leagueScheduleFragment = new LeagueScheduleFragment();
        Bundle args = new Bundle();
        args.putString("key", leagueKey);
        leagueScheduleFragment.setArguments(args);
        return leagueScheduleFragment;
    }

    private Fragment getStandingFragment() {
        StandingFragment standingFragment = new StandingFragment();
        Bundle args1 = new Bundle();
        args1.putString("key", leagueKey);
        standingFragment.setArguments(args1);
        return standingFragment;
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
