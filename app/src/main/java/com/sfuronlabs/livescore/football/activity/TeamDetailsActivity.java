package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.SquadFragment;
import com.sfuronlabs.livescore.football.fragments.TeamInfoFragment;
import com.sfuronlabs.livescore.football.fragments.TransferFragment;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.Constants;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_league_details)
public class TeamDetailsActivity extends RoboAppCompatActivity {

    String[] titleText = new String[]{"Team Info","Squad", "Transfers"};
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @InjectView(R.id.pager)
    private ViewPager mViewPager;

    @InjectView(R.id.tab_layout)
    private TabLayout tabLayout;

    @InjectView(R.id.adview_league_details)
    private AdView adView;

    private Team team;

    @Inject
    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("ripon", "http://static.holoduke.nl/footapi/team_gs/"+getIntent().getStringExtra("teamKey")+".json");
        networkService.fetchTeamDetails(getIntent().getStringExtra("teamKey"), new DefaultMessageHandler(this, true) {
            @Override
            public void onSuccess(Message msg) {
                team = (Team) msg.obj;

                setTitle(team.getTeamName());

                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(Constants.ONE_PLUS_TEST_DEVICE)
                .addTestDevice(Constants.XIAOMI_TEST_DEVICE).build();
        adView.loadAd(adRequest);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                TeamInfoFragment fragment = new TeamInfoFragment();
                Bundle args = new Bundle();
                args.putSerializable("teamDetails", team);
                fragment.setArguments(args);
                return fragment;
            } else if (position == 1) {
                SquadFragment fragment = new SquadFragment();
                Bundle args = new Bundle();
                args.putSerializable("teamDetails", team);
                fragment.setArguments(args);
                return fragment;
            } else {
                TransferFragment fragment = new TransferFragment();
                Bundle args = new Bundle();
                args.putSerializable("teamDetails", team);
                fragment.setArguments(args);
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return titleText.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleText[position];
        }
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
