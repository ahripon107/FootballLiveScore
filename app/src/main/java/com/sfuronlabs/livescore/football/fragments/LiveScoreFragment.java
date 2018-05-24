package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.MatchDetailsActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.CountryLeague;
import com.sfuronlabs.livescore.football.model.MatchSummary;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.DividerItemDecoration;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class LiveScoreFragment extends RoboFragment {

    @Inject
    private NetworkService networkService;

    @InjectView(R.id.list)
    private RecyclerView liveMatchesList;

    @InjectView(R.id.tv_empty)
    private TextView emptyView;

    private List<CountryLeague> countryLeagues = new ArrayList<>();

    private ArrayList<MatchSummary> liveMatches = new ArrayList<>();

    private BasicListAdapter<MatchSummary, LiveMatchesVieaHolder> liveMatchesListAdapter;
    private Player player;
    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("ripon", "http://static.holoduke.nl/footapi/fixtures/feed_livenow.json");

        liveMatchesListAdapter = new BasicListAdapter<MatchSummary, LiveMatchesVieaHolder>(liveMatches) {
            @Override
            public LiveMatchesVieaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item, parent, false);
                return new LiveMatchesVieaHolder(view);
            }

            @Override
            public void onBindViewHolder(LiveMatchesVieaHolder holder, int position) {
                final MatchSummary liveMatch = liveMatches.get(position);

                holder.leagueName.setText(liveMatch.getFileGroup() + " - " + liveMatch.getLeagueName());
                holder.localTeam.setText(liveMatch.getLocalTeam());
                holder.visitorTeam.setText(liveMatch.getVisitorTeam());
                holder.scoreLine.setText(liveMatch.getScoreTime());
                if (liveMatch.getStatus().equals("HT") || liveMatch.getStatus().equals("FT")) {
                    holder.minute.setText(liveMatch.getStatus());
                } else {
                    holder.minute.setText(liveMatch.getStatus() + "'");
                }

                holder.minute.setTextColor(ContextCompat.getColor(getContext(), R.color.Green));

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/" +
                        liveMatch.getLocalTeamId() + "_small.png").into(holder.localTeamLogo);

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/" +
                        liveMatch.getVisitorTeamId() + "_small.png").into(holder.visitorTeamLogo);

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MatchDetailsActivity.class);
                        intent.putExtra("matchId", liveMatch.getId());
                        startActivity(intent);
                    }
                });

            }
        };

        networkService.fetchLiveNow(new DefaultMessageHandler(getContext(), true) {
            @Override
            public void onSuccess(Message msg) {
                countryLeagues = (List<CountryLeague>) msg.obj;
                liveMatches.clear();
                for (int i = 0; i < countryLeagues.size(); i++) {
                    for (int j = 0; j < countryLeagues.get(i).getLeagues().size(); j++) {
                        liveMatches.addAll(countryLeagues.get(i).getLeagues().get(j).getMatches());
                    }

                    //Log.d("ripon", countryLeagues.get(i).toString());
                }
                liveMatchesListAdapter.notifyDataSetChanged();
                if (liveMatches.size() > 0) {
                    emptyView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("No live matches ongoing");
                }
            }
        });

        liveMatchesList.setAdapter(liveMatchesListAdapter);
        liveMatchesList.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        liveMatchesList.addItemDecoration(itemDecoration);
    }

    private static class LiveMatchesVieaHolder extends RecyclerView.ViewHolder {
        protected TextView leagueName;
        protected TextView localTeam;
        protected ImageView localTeamLogo;
        protected TextView visitorTeam;
        protected ImageView visitorTeamLogo;
        protected TextView scoreLine;
        protected TextView minute;
        protected LinearLayout linearLayout;

        public LiveMatchesVieaHolder(View itemView) {
            super(itemView);
            leagueName = ViewHolder.get(itemView, R.id.tv_league_name);
            localTeam = ViewHolder.get(itemView, R.id.tv_local_team);
            localTeamLogo = ViewHolder.get(itemView, R.id.logo_local_team);
            visitorTeam = ViewHolder.get(itemView, R.id.tv_visitor_team);
            visitorTeamLogo = ViewHolder.get(itemView, R.id.logo_visitor_team);
            scoreLine = ViewHolder.get(itemView, R.id.tv_score);
            minute = ViewHolder.get(itemView, R.id.tv_minute);
            linearLayout = ViewHolder.get(itemView, R.id.linear_layout);
        }
    }
}
