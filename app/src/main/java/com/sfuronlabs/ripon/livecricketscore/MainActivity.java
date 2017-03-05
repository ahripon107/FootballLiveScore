package com.sfuronlabs.ripon.livecricketscore;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboAppCompatActivity {

    @Inject
    NetworkService networkService;

    private List<CountryLeague> countryLeagues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        networkService.fetchLiveNow(new DefaultMessageHandler(this,true){
            @Override
            public void onSuccess(Message msg) {
                countryLeagues = (List<CountryLeague>) msg.obj;
                Toast.makeText(MainActivity.this,countryLeagues.size()+"",Toast.LENGTH_LONG).show();
            }
        });
    }

}
