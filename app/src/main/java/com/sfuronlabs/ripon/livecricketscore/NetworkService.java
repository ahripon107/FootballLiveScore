package com.sfuronlabs.ripon.livecricketscore;

import android.os.Handler;

import com.google.inject.Inject;
import com.loopj.android.http.AsyncHttpClient;

import java.util.List;

/**
 * @author Ripon
 */

public class NetworkService {
    @Inject
    private AsyncHttpClient httpClient;

    public void fetchLiveNow(Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/fixtures/feed_livenow.json",
                new DefaultAsyncHttpResponseHandler(handler,CountryLeague.class, List.class));
    }
}
