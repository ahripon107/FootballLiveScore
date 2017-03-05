package com.sfuronlabs.livescore.football.service;

import android.os.Handler;

import com.google.inject.Inject;
import com.loopj.android.http.AsyncHttpClient;
import com.sfuronlabs.livescore.football.model.CountryLeague;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultAsyncHttpResponseHandler;

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

    public void fetchPlayerCareer(String id, Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/players/"+id+".json", new DefaultAsyncHttpResponseHandler(handler, Player.class));
    }

    public void fetchTeamDetails(String id, Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/team_gs/"+id+".json", new DefaultAsyncHttpResponseHandler(handler, Team.class));
    }
}
