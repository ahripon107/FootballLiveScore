package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.model.CountryLeague;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import java.util.List;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboAppCompatActivity {

    @Inject
    NetworkService networkService;

    private List<CountryLeague> countryLeagues;
    private Player player;
    private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("ripon", "http://static.holoduke.nl/footapi/fixtures/feed_livenow.json");

        networkService.fetchLiveNow(new DefaultMessageHandler(this,true){
            @Override
            public void onSuccess(Message msg) {
                countryLeagues = (List<CountryLeague>) msg.obj;
                for (int i=0;i<countryLeagues.size();i++) {
                    Log.d("ripon", countryLeagues.get(i).toString());
                }

                Toast.makeText(MainActivity.this,countryLeagues.size()+"",Toast.LENGTH_LONG).show();
            }
        });

        networkService.fetchPlayerCareer("2290", new DefaultMessageHandler(this,true) {
            @Override
            public void onSuccess(Message msg) {
                player = (Player) msg.obj;
                Log.d("ripon", player.toString());
            }
        });

        networkService.fetchTeamDetails("16110", new DefaultMessageHandler(this,false){
            @Override
            public void onSuccess(Message msg) {
                team = (Team) msg.obj;
                Log.d("ripon", team.getFixtures().toString());
            }
        });
    }

}
