package com.sfuronlabs.livescore.football.service;

import android.os.Handler;

import com.google.inject.Inject;
import com.loopj.android.http.AsyncHttpClient;
import com.sfuronlabs.livescore.football.model.Commentary;
import com.sfuronlabs.livescore.football.model.LiveTicker;
import com.sfuronlabs.livescore.football.model.CountryLeague;
import com.sfuronlabs.livescore.football.model.MatchDetails;
import com.sfuronlabs.livescore.football.model.MatchSummary;
import com.sfuronlabs.livescore.football.model.News;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.PointTable;
import com.sfuronlabs.livescore.football.model.Team;

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

    public void fetchMatchDetails(String matchId, Handler handler) {
        httpClient.get("http://holoduke.nl/footapi/matches/"+matchId+".json", new DefaultAsyncHttpResponseHandler(handler, MatchDetails.class));
    }

    public void fetchCommentry(String matchId, Handler handler) {
        httpClient.get("http://holoduke.nl/footapi/commentaries/"+matchId+".json",new DefaultAsyncHttpResponseHandler(handler, Commentary.class));
    }

    public void fetchLeagueSchedule(String key, Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/fixtures/"+key+"_small.json", new DefaultAsyncHttpResponseHandler(handler,
                MatchSummary.class, List.class));
    }

    public void fetchLeagueStandings(String key, Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/tables/"+key+".json", new DefaultAsyncHttpResponseHandler(handler,
                PointTable.class));
    }

    public void fetchNewsList(Handler handler) {
        httpClient.get("http://static.holoduke.nl/footapi/news/US.json", new DefaultAsyncHttpResponseHandler(handler, News.class));
    }
}
