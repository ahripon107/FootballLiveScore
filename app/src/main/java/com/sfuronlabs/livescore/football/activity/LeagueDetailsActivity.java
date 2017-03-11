package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.LeagueScheduleFragment;
import com.sfuronlabs.livescore.football.fragments.LineupsFragment;
import com.sfuronlabs.livescore.football.fragments.MatchInfoFragment;
import com.sfuronlabs.livescore.football.fragments.StandingFragment;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_league_details)
public class LeagueDetailsActivity extends RoboAppCompatActivity {

    String[] titleText = new String[]{"Schedule", "Standings"};
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private String leagueKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        leagueKey = getIntent().getStringExtra("leagueKey");
        mViewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position ==0) {
                LeagueScheduleFragment fragment = new LeagueScheduleFragment();
                Bundle args = new Bundle();
                args.putString("key", leagueKey);
                fragment.setArguments(args);
                return fragment;
            } else {
                StandingFragment fragment = new StandingFragment();
                Bundle args = new Bundle();
                args.putString("key", leagueKey);
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
