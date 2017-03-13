package com.sfuronlabs.livescore.football.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_news_details)
public class NewsDetailsActivity extends RoboAppCompatActivity{

    public static final String EXTRA_URL = "url";

    @InjectView(R.id.webView)
    private WebView mWebview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);

        setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = getIntent().getStringExtra(EXTRA_URL);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(NewsDetailsActivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (pd != null) {
                    pd.dismiss();
                }
            }
        });


        mWebview .loadUrl(url);
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
