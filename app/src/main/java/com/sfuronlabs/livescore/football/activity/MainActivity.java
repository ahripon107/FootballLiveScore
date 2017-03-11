package com.sfuronlabs.livescore.football.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.fragments.LiveScoreFragment;
import com.sfuronlabs.livescore.football.fragments.NewsFragment;
import com.sfuronlabs.livescore.football.model.CountryLeague;
import com.sfuronlabs.livescore.football.model.MatchSummary;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboAppCompatActivity {



    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    String[] titleText = new String[]{"Live Matches", "News"};

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


//        networkService.fetchPlayerCareer("2290", new DefaultMessageHandler(this,true) {
//            @Override
//            public void onSuccess(Message msg) {
//                player = (Player) msg.obj;
//                Log.d("ripon", player.toString());
//            }
//        });
//
//        networkService.fetchTeamDetails("16110", new DefaultMessageHandler(this,false){
//            @Override
//            public void onSuccess(Message msg) {
//                team = (Team) msg.obj;
//                Log.d("ripon", team.getFixtures().toString());
//            }
//        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                LiveScoreFragment fragment = new LiveScoreFragment();
                return fragment;
            } else {
                NewsFragment fragment = new NewsFragment();
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
