package com.sfuronlabs.livescore.football.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.fragments.HomeFragment;
import com.sfuronlabs.livescore.football.fragments.LiveScoreFragment;
import com.sfuronlabs.livescore.football.fragments.NewsFragment;
import com.sfuronlabs.livescore.football.util.Constants;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboAppCompatActivity {

    private String version = "";
    private InterstitialAd mInterstitialAd;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(getString(R.string.app_name));
                    return setupFragment(new HomeFragment());
                case R.id.navigation_news:
                    setTitle(getString(R.string.title_news));
                    return setupFragment(new NewsFragment());
                case R.id.navigation_live_matches:
                    setTitle(getString(R.string.title_live_matches));
                    return setupFragment(new LiveScoreFragment());
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        if (Constants.showPopupMessage && Constants.versions.contains(version)) {
            new AlertDialog.Builder(this)
                    .setMessage(Constants.popupMessage)
                    .setPositiveButton(Constants.positiveButton, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Constants.linkAvailable) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.playStoreUrl)));
                            }
                        }
                    }).setNegativeButton(Constants.negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.frontpageinterstitial));

        AdRequest adRequestInterstitial = new AdRequest.Builder()
                .addTestDevice(Constants.ONE_PLUS_TEST_DEVICE).addTestDevice(Constants.XIAOMI_TEST_DEVICE)
                .build();
        mInterstitialAd.loadAd(adRequestInterstitial);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setupFragment(new HomeFragment());
    }

    private boolean setupFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment) instanceof HomeFragment) {
            super.onBackPressed();
        } else {
            setTitle(getString(R.string.app_name));
            setupFragment(new HomeFragment());
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }
}
