package com.sfuronlabs.livescore.football.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.sfuronlabs.livescore.football.R;

/**
 * @author Ripon
 */

public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        final Thread t;
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent1 = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            }

        });
        t.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
