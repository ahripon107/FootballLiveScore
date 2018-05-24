package com.sfuronlabs.livescore.football.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.LineupsFragment;
import com.sfuronlabs.livescore.football.fragments.MatchInfoFragment;
import com.sfuronlabs.livescore.football.model.MatchDetails;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.Constants;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;
import com.squareup.picasso.Picasso;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_match_details)
public class MatchDetailsActivity extends RoboAppCompatActivity {

    String[] titleText = new String[]{"Match Info", "Lineups"};
    @Inject
    private NetworkService networkService;
    @InjectView(R.id.tv_local_team)
    private TextView localTeam;
    @InjectView(R.id.tv_date)
    private TextView date;
    @InjectView(R.id.tv_score)
    private TextView scoreline;
    @InjectView(R.id.tv_time)
    private TextView time;
    @InjectView(R.id.tv_visitor_team)
    private TextView visitorTeam;
    @InjectView(R.id.logo_local_team)
    private ImageView localTeamLogo;
    @InjectView(R.id.logo_visitor_team)
    private ImageView visitorTeamLogo;
    @InjectView(R.id.adview_match_details)
    private AdView adView;
    private MatchDetails matchDetails;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private String matchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        matchId = getIntent().getStringExtra("matchId");
        mViewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);

        loadData();

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(Constants.ONE_PLUS_TEST_DEVICE)
                .addTestDevice(Constants.XIAOMI_TEST_DEVICE).build();
        adView.loadAd(adRequest);
    }

    private void loadData() {
        Log.d("ripon", "http://holoduke.nl/footapi/matches/" + matchId + ".json");

        networkService.fetchMatchDetails(matchId, new DefaultMessageHandler(this, true) {
            @Override
            public void onSuccess(Message msg) {
                matchDetails = (MatchDetails) msg.obj;

                setTitle(matchDetails.getLocalTeam() + " vs " + matchDetails.getVisitorTeam());
                Log.d("ripon", matchDetails.toString());
                localTeam.setText(matchDetails.getLocalTeam());
                visitorTeam.setText(matchDetails.getVisitorTeam());
                date.setText(matchDetails.getDate());
                scoreline.setText(matchDetails.getScoreLine());
                if (matchDetails.getStatus().equals("HT") || matchDetails.getStatus().equals("FT")) {
                    time.setText(matchDetails.getStatus());
                } else {
                    time.setText(matchDetails.getStatus() + "'");
                }

                time.setTextColor(ContextCompat.getColor(MatchDetailsActivity.this, R.color.Green));

                Picasso.with(MatchDetailsActivity.this).load("http://static.holoduke.nl/footapi/images/teams_gs/" +
                        matchDetails.getLocalTeamId() + "_small.png").into(localTeamLogo);

                Picasso.with(MatchDetailsActivity.this).load("http://static.holoduke.nl/footapi/images/teams_gs/" +
                        matchDetails.getVisitorTeamId() + "_small.png").into(visitorTeamLogo);

                localTeamLogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MatchDetailsActivity.this, TeamDetailsActivity.class);
                        intent.putExtra("teamKey", matchDetails.getLocalTeamId());
                        startActivity(intent);
                    }
                });

                visitorTeamLogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MatchDetailsActivity.this, TeamDetailsActivity.class);
                        intent.putExtra("teamKey", matchDetails.getVisitorTeamId());
                        startActivity(intent);
                    }
                });

                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_load:
                loadData();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                MatchInfoFragment fragment = new MatchInfoFragment();
                Bundle args = new Bundle();
                args.putSerializable("matchdetails", matchDetails);
                fragment.setArguments(args);
                return fragment;
            } else {
                LineupsFragment fragment = new LineupsFragment();
                Bundle args = new Bundle();
                args.putSerializable("matchdetails", matchDetails);
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
