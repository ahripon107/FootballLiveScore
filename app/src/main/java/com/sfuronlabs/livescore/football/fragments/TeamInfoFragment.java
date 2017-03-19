package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.model.TeamLeague;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class TeamInfoFragment extends RoboFragment{

    @InjectView(R.id.league_list)
    private RecyclerView competitionList;

    @InjectView(R.id.match_list)
    private RecyclerView scheduleList;

    @InjectView(R.id.tv_founded)
    private TextView founded;

    @InjectView(R.id.tv_coach)
    private TextView coach;

    @InjectView(R.id.tv_country)
    private TextView country;

    @InjectView(R.id.tv_city)
    private TextView city;

    @InjectView(R.id.team_logo)
    private ImageView teamLogo;

    @Inject
    private ArrayList<TeamLeague> competitions;

    @Inject
    private ArrayList<MatchSummary> schedules;

    private BasicListAdapter<TeamLeague, CompetitionsViewHolder> competitionListAdapter;
    private BasicListAdapter<MatchSummary, ScheduleVieaHolder> scheduleListAdapter;
    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        team = (Team) getArguments().getSerializable("teamDetails");
        competitionList.setNestedScrollingEnabled(false);
        scheduleList.setNestedScrollingEnabled(false);

        if (team.getFounded() != null) {
            founded.setText(Html.fromHtml("<b>Founded: </b>"+team.getFounded()));
        } else {
            founded.setText(Html.fromHtml("<b>Founded: </b>"));
        }

        if (team.getCoach() != null) {
            coach.setText(Html.fromHtml("<b>Coach: </b>"+team.getCoach()));
        } else {
            coach.setText(Html.fromHtml("<b>Coach: </b>"));
        }

        if (team.getCountry() != null) {
            country.setText(Html.fromHtml("<b>Country: </b>"+team.getCountry()));
        } else {
            country.setText(Html.fromHtml("<b>Country: </b>"));
        }

        if (team.getVenueCity() != null) {
            city.setText(Html.fromHtml("<b>City: </b>"+team.getVenueCity()));
        } else {
            city.setText(Html.fromHtml("<b>City: </b>"));
        }


        Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/"+
                team.getId()+"_small.png").into(teamLogo);

        competitionListAdapter = new BasicListAdapter<TeamLeague, CompetitionsViewHolder>(competitions) {
            @Override
            public CompetitionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_team_squad,parent,false);
                return new CompetitionsViewHolder(view);
            }

            @Override
            public void onBindViewHolder(CompetitionsViewHolder holder, int position) {
                TeamLeague teamLeague = competitions.get(position);
                holder.name.setText(teamLeague.getLeague());
                if (teamLeague.getPosition() != null) {
                    holder.description.setText(teamLeague.getCountry()+" - Position: "+teamLeague.getPosition());
                } else {
                    holder.description.setText("");
                }

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/flags/"+prepareCountryName(teamLeague.getCountry())+".png").into(holder.imageView);
            }
        };

        competitionList.setAdapter(competitionListAdapter);
        competitionList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (team.getLeagues() != null) {
            competitions.addAll(team.getLeagues());
            competitionListAdapter.notifyDataSetChanged();
        }

        scheduleListAdapter = new BasicListAdapter<MatchSummary, ScheduleVieaHolder>(schedules) {
            @Override
            public ScheduleVieaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item,parent,false);
                return new ScheduleVieaHolder(view);
            }

            @Override
            public void onBindViewHolder(ScheduleVieaHolder holder, int position) {
                final MatchSummary matchSummary = schedules.get(position);
                holder.localTeam.setText(matchSummary.getLocalTeam());
                holder.visitorTeam.setText(matchSummary.getVisitorTeam());
                holder.leagueName.setTextSize(12.0f);
                holder.leagueName.setText(matchSummary.getDate()+" - "+matchSummary.getLeagueName());

                holder.scoreLine.setText(matchSummary.getScoreTime());
                holder.minute.setText("");

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/"+
                        matchSummary.getLocalTeamId()+"_small.png").into(holder.localTeamLogo);

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/"+
                        matchSummary.getVisitorTeamId()+"_small.png").into(holder.visitorTeamLogo);

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MatchDetailsActivity.class);
                        intent.putExtra("matchId", matchSummary.getId());
                        startActivity(intent);
                    }
                });
            }
        };

        scheduleList.setAdapter(scheduleListAdapter);
        scheduleList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (team.getFixtures() != null) {
            schedules.addAll(team.getFixtures());
            scheduleListAdapter.notifyDataSetChanged();
        }

    }

    private String prepareCountryName(String str) {
        return str.replace(' ', '-').toLowerCase();
    }

    private static class CompetitionsViewHolder extends RecyclerView.ViewHolder {
        protected CircleImageView imageView;
        protected TextView name;
        protected TextView description;
        protected CardView cardView;


        public CompetitionsViewHolder(View itemView) {
            super(itemView);

            imageView = ViewHolder.get(itemView, R.id.image);
            name = ViewHolder.get(itemView, R.id.tv_name);
            description = ViewHolder.get(itemView, R.id.tv_description);
            cardView = ViewHolder.get(itemView, R.id.squal_player_card);

        }
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
