package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.sfuronlabs.livescore.football.activity.TeamDetailsActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.PointTable;
import com.sfuronlabs.livescore.football.model.TeamStanding;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class StandingFragment extends RoboFragment{

    @InjectView(R.id.list)
    private RecyclerView standingList;

    @Inject
    private ArrayList<TeamStanding> standings;

    @Inject
    private NetworkService networkService;

    private BasicListAdapter<TeamStanding, StandingViewHolder> standingListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        standingListAdapter = new BasicListAdapter<TeamStanding, StandingViewHolder>(standings) {
            @Override
            public StandingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standing_list_item,parent,false);
                return new StandingViewHolder(view);
            }

            @Override
            public void onBindViewHolder(StandingViewHolder holder, int position) {
                final TeamStanding teamStanding = standings.get(position);
                holder.localTeamName.setText(teamStanding.getTeam());
                holder.localTeamGp.setText(teamStanding.getTotalPlayed());
                holder.localTeamWins.setText(teamStanding.getTotalWon());
                holder.localTeamDraws.setText(teamStanding.getTotalDraw());
                holder.localTeamLoss.setText(teamStanding.getTotalLost());
                holder.localTeamGd.setText(teamStanding.getGoalDifference());
                holder.localTeamPts.setText(teamStanding.getPoints());

                if (position != 0) {
                    Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/teams_gs/"+
                            teamStanding.getTeamId()+"_small.png").into(holder.logo);
                }

                holder.standingsLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), TeamDetailsActivity.class);
                        intent.putExtra("teamKey", teamStanding.getTeamId());
                        getActivity().startActivity(intent);
                    }
                });
            }
        };

        standingList.setAdapter(standingListAdapter);
        standingList.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("ripon", "http://static.holoduke.nl/footapi/tables/"+getArguments().getString("key")+".json");
        networkService.fetchLeagueStandings(getArguments().getString("key"), new DefaultMessageHandler(getContext(), true){
            @Override
            public void onSuccess(Message msg) {
                PointTable pointTable = (PointTable) msg.obj;
                TeamStanding teamS = new TeamStanding();
                teamS.setTeamId("");
                teamS.setPosition("");
                teamS.setTeam("Team");
                teamS.setTotalPlayed("GP");
                teamS.setTotalWon("W");
                teamS.setTotalDraw("D");
                teamS.setTotalLost("L");
                teamS.setTotalGoalsFor("");
                teamS.setTotalGoalsAgainst("");
                teamS.setGoalDifference("GD");
                teamS.setPoints("Pts");
                teamS.setDescription("");
                standings.add(teamS);

                for (int i=0;i<pointTable.getGroups().size();i++) {
                    standings.addAll(pointTable.getGroups().get(i).getTeams());
                }
                standingListAdapter.notifyDataSetChanged();
            }
        });
    }

    private static class StandingViewHolder extends RecyclerView.ViewHolder {

        private TextView localTeamName;
        private TextView localTeamGp;
        private TextView localTeamWins;
        private TextView localTeamDraws;
        private TextView localTeamLoss;
        private TextView localTeamGd;
        private TextView localTeamPts;
        private ImageView logo;
        private LinearLayout standingsLayout;

        public StandingViewHolder(View itemView) {
            super(itemView);

            localTeamName = ViewHolder.get(itemView, R.id.tv_local_team_name);
            localTeamGp = ViewHolder.get(itemView, R.id.tv_local_team_gp);
            localTeamWins = ViewHolder.get(itemView, R.id.tv_local_team_wins);
            localTeamDraws = ViewHolder.get(itemView, R.id.tv_local_team_draw);
            localTeamLoss = ViewHolder.get(itemView, R.id.tv_local_team_loss);
            localTeamGd = ViewHolder.get(itemView, R.id.tv_local_team_gd);
            localTeamPts = ViewHolder.get(itemView, R.id.tv_local_team_pts);
            logo = ViewHolder.get(itemView, R.id.img_team_logo);
            standingsLayout = ViewHolder.get(itemView, R.id.standings_layout);
        }
    }
}
