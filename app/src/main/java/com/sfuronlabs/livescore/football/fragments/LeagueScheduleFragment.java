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
import com.sfuronlabs.livescore.football.model.MatchSummary;
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

public class LeagueScheduleFragment extends RoboFragment {
    @InjectView(R.id.list)
    private RecyclerView scheduleList;

    @Inject
    private ArrayList<MatchSummary> schedules;

    @Inject
    private NetworkService networkService;

    private BasicListAdapter<MatchSummary, ScheduleVieaHolder> scheduleListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scheduleListAdapter = new BasicListAdapter<MatchSummary, ScheduleVieaHolder>(schedules) {
            @Override
            public ScheduleVieaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item, parent, false);
                return new ScheduleVieaHolder(view);
            }

            @Override
            public void onBindViewHolder(ScheduleVieaHolder holder, int position) {
                final MatchSummary liveMatch = schedules.get(position);

                holder.leagueName.setText(liveMatch.getDate());
                holder.leagueName.setTextSize(12.0f);
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
                        Intent intent = new Intent(getActivity(), MatchDetailsActivity.class);
                        intent.putExtra("matchId", liveMatch.getId());
                        startActivity(intent);
                    }
                });
            }
        };

        scheduleList.setAdapter(scheduleListAdapter);
        scheduleList.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        scheduleList.addItemDecoration(itemDecoration);

        Log.d("ripon", "http://static.holoduke.nl/footapi/fixtures/" + getArguments().getString("key") + "_small.json");
        networkService.fetchLeagueSchedule(getArguments().getString("key"), new DefaultMessageHandler(getContext(), true) {
            @Override
            public void onSuccess(Message msg) {
                List<MatchSummary> matchSummaries = (List<MatchSummary>) msg.obj;
                schedules.addAll(matchSummaries);
                scheduleListAdapter.notifyDataSetChanged();
                int position = schedules.size() - 1;
                for (int i = schedules.size() - 1; i >= 0; i--) {
                    if (schedules.get(i).getStatus().equals("FT")) {
                        position = i;
                        break;
                    }
                }

                scheduleList.scrollToPosition(position);
            }
        });

    }

    private static class ScheduleVieaHolder extends RecyclerView.ViewHolder {
        protected TextView leagueName;
        protected TextView localTeam;
        protected ImageView localTeamLogo;
        protected TextView visitorTeam;
        protected ImageView visitorTeamLogo;
        protected TextView scoreLine;
        protected TextView minute;
        protected LinearLayout linearLayout;

        public ScheduleVieaHolder(View itemView) {
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
