package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.SquadFragment;
import com.sfuronlabs.livescore.football.fragments.TransferFragment;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_league_details)
public class TeamDetailsActivity extends RoboAppCompatActivity {

    String[] titleText = new String[]{"Squad", "Transfers"};
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @InjectView(R.id.pager)
    private ViewPager mViewPager;

    @InjectView(R.id.tab_layout)
    private TabLayout tabLayout;
    private Team team;

    @Inject
    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
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
}
