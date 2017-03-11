package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.LeagueDetailsActivity;
import com.sfuronlabs.livescore.football.activity.MatchDetailsActivity;
import com.sfuronlabs.livescore.football.activity.PlayerInfoActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.Commentary;
import com.sfuronlabs.livescore.football.model.LiveTicker;
import com.sfuronlabs.livescore.football.model.MatchDetails;
import com.sfuronlabs.livescore.football.model.MatchEvent;
import com.sfuronlabs.livescore.football.model.MatchTeamStat;
import com.sfuronlabs.livescore.football.model.TeamStanding;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class MatchInfoFragment extends RoboFragment{

    @InjectView(R.id.match_events_list)
    private RecyclerView matchEventList;

    @InjectView(R.id.match_stats_list)
    private RecyclerView matchStatsList;

    @InjectView(R.id.commentry_list)
    private RecyclerView commentryList;

    @InjectView(R.id.tv_league_name)
    private TextView leagueName;

    @InjectView(R.id.tv_stadium_name)
    private TextView stadiumName;

    @InjectView(R.id.tv_local_team_rank)
    private TextView localTeamRank;

    @InjectView(R.id.tv_local_team_name)
    private TextView localTeamName;

    @InjectView(R.id.tv_local_team_gp)
    private TextView localTeamGp;

    @InjectView(R.id.tv_local_team_wins)
    private TextView localTeamWins;

    @InjectView(R.id.tv_local_team_draw)
    private TextView localTeamDraws;

    @InjectView(R.id.tv_local_team_loss)
    private TextView localTeamLoss;

    @InjectView(R.id.tv_local_team_gd)
    private TextView localTeamGd;

    @InjectView(R.id.tv_local_team_pts)
    private TextView localTeamPts;

    @InjectView(R.id.tv_visitor_team_rank)
    private TextView visitorTeamRank;

    @InjectView(R.id.tv_visitor_team_name)
    private TextView visitorTeamName;

    @InjectView(R.id.tv_visitor_team_gp)
    private TextView visitorTeamGp;

    @InjectView(R.id.tv_visitor_team_wins)
    private TextView visitorTeamWins;

    @InjectView(R.id.tv_visitor_team_draw)
    private TextView visitorTeamDraws;

    @InjectView(R.id.tv_visitor_team_loss)
    private TextView visitorTeamLoss;

    @InjectView(R.id.tv_visitor_team_gd)
    private TextView visitorTeamGd;

    @InjectView(R.id.tv_visitor_team_pts)
    private TextView visitorTeamPts;

    @InjectView(R.id.tv_standings)
    private TextView standings;

    @InjectView(R.id.card_view)
    private CardView cardView;

    @InjectView(R.id.tv_local_wins)
    private TextView localWins;

    @InjectView(R.id.tv_visitor_wins)
    private TextView visitorWins;

    @InjectView(R.id.tv_local_goals)
    private TextView localGoals;

    @InjectView(R.id.tv_visitor_goals)
    private TextView visitorGoals;

    @InjectView(R.id.card_head2head)
    private CardView cardHead2Head;

    private BasicListAdapter<MatchEvent, MatchEventViewHolder> matchEventListAdapter;
    private BasicListAdapter<StatModel, StatsViewHolder> matchStatsListAdapter;
    private BasicListAdapter<LiveTicker, TickerViewHolder> commentryListAdapter;

    private MatchDetails matchDetails;

    @Inject
    private ArrayList<MatchEvent> matchEvents;

    @Inject
    private ArrayList<StatModel> stats;

    @Inject
    private ArrayList<LiveTicker> commentries;

    @Inject
    NetworkService networkService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_info,container,false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        matchDetails = (MatchDetails) getArguments().getSerializable("matchdetails");
        matchEventList.setNestedScrollingEnabled(false);
        matchStatsList.setNestedScrollingEnabled(false);
        commentryList.setNestedScrollingEnabled(false);

        leagueName.setText(matchDetails.getCountryOfLeague()+" - "+matchDetails.getLeagueName());
        stadiumName.setText(matchDetails.getVenue());

        leagueName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LeagueDetailsActivity.class);
                intent.putExtra("leagueKey", matchDetails.getLeagueKey());
                startActivity(intent);
            }
        });

        matchEventListAdapter = new BasicListAdapter<MatchEvent, MatchEventViewHolder>(matchEvents) {
            @Override
            public MatchEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_event_list_item,parent,false);
                return new MatchEventViewHolder(view);
            }

            @Override
            public void onBindViewHolder(MatchEventViewHolder holder, int position) {
                final MatchEvent matchEvent = matchEvents.get(position);
                if (matchEvent.getTeam().equals("localteam")) {
                    holder.rightEvent.setVisibility(View.GONE);
                    holder.rightPlayer.setVisibility(View.GONE);
                    holder.rightPlayerName.setVisibility(View.GONE);
                    Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/"+
                            matchEvent.getPlayerId()+"_small.png").into(holder.leftPlayer);
                    holder.leftPlayerName.setText(matchEvent.getPlayer());
                    holder.minute.setText(matchEvent.getMinute()+"'");
                    if (matchEvent.getType().equals("goal")) {
                        holder.leftEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.football));
                    } else if (matchEvent.getType().equals("yellowcard")) {
                        holder.leftEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.yellowcard));
                    } else if (matchEvent.getType().equals("redcard")) {
                        holder.leftEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.redcard));
                    }
                } else {
                    holder.leftEvent.setVisibility(View.GONE);
                    holder.leftPlayer.setVisibility(View.GONE);
                    holder.leftPlayerName.setVisibility(View.GONE);
                    Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/"+
                            matchEvent.getPlayerId()+"_small.png").into(holder.rightPlayer);
                    holder.rightPlayerName.setText(matchEvent.getPlayer());
                    holder.minute.setText(matchEvent.getMinute()+"'");
                    if (matchEvent.getType().equals("goal")) {
                        holder.rightEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.football));
                    } else if (matchEvent.getType().equals("yellowcard")) {
                        holder.rightEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.yellowcard));
                    } else if (matchEvent.getType().equals("redcard")) {
                        holder.rightEvent.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.redcard));
                    }
                }

                holder.leftLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPlayerProfileActivity(matchEvent.getPlayerId());
                    }
                });

                holder.rightLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPlayerProfileActivity(matchEvent.getPlayerId());
                    }
                });
            }
        };

        matchEventList.setAdapter(matchEventListAdapter);
        matchEventList.setLayoutManager(new LinearLayoutManager(getContext()));

        matchStatsListAdapter = new BasicListAdapter<StatModel, StatsViewHolder>(stats) {
            @Override
            public StatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_stat_list_item,parent,false);
                return new StatsViewHolder(view);
            }

            @Override
            public void onBindViewHolder(StatsViewHolder holder, int position) {
                StatModel statModel = stats.get(position);
                holder.localTeamScore.setText(statModel.getLocalTeamScore());
                holder.criteria.setText(statModel.getCriteria());
                holder.visitorTeamScore.setText(statModel.getVisitorTeamScore());
            }
        };

        matchStatsList.setAdapter(matchStatsListAdapter);
        matchStatsList.setLayoutManager(new LinearLayoutManager(getContext()));

        commentryListAdapter = new BasicListAdapter<LiveTicker, TickerViewHolder>(commentries) {
            @Override
            public TickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentry_list_item,parent,false);
                return new TickerViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TickerViewHolder holder, int position) {
                holder.time.setText(commentries.get(position).getMinute());
                holder.commentry.setText(commentries.get(position).getComment());
            }
        };

        commentryList.setAdapter(commentryListAdapter);
        commentryList.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("ripon", "http://holoduke.nl/footapi/commentaries/"+matchDetails.getId()+".json");

        networkService.fetchCommentry(matchDetails.getId(), new DefaultMessageHandler(getContext(), false){
            @Override
            public void onSuccess(Message msg) {
                Commentary commentry = (Commentary) msg.obj;
                Log.d("riponc", commentry.toString());
                commentries.clear();
                commentries.addAll(commentry.getLiveticker());
                commentryListAdapter.notifyDataSetChanged();
            }
        });

        matchEvents.clear();
        matchEvents.addAll(matchDetails.getEvents());
        matchEventListAdapter.notifyDataSetChanged();


        if (matchDetails.getCommentaries() != null) {
            Log.d("ripon", matchDetails.getCommentaries().toString());
            stats.clear();
            if (matchDetails.getCommentaries().getStats() != null) {
                MatchTeamStat local = matchDetails.getCommentaries().getStats().getLocalTeamStat();
                MatchTeamStat visitor = matchDetails.getCommentaries().getStats().getVisitorTeamStat();
                stats.add(new StatModel("Total Shots",local.getTotalShots(),visitor.getTotalShots()));
                stats.add(new StatModel("Shots on Goal", local.getShotsToGoal(), visitor.getShotsToGoal()));
                stats.add(new StatModel("Fouls",local.getFouls(), visitor.getFouls()));
                stats.add(new StatModel("Corners",local.getCorners(), visitor.getCorners()));
                stats.add(new StatModel("Offsides", local.getOffsides(), visitor.getOffsides()));
                stats.add(new StatModel("Possessation Time", local.getPossessationTime(), visitor.getPossessationTime()));
                stats.add(new StatModel("Yellow Cards", local.getYellowCards(), visitor.getYellowCards()));
                stats.add(new StatModel("Red Cards", local.getRedCards(), visitor.getRedCards()));
                stats.add(new StatModel("Saves", local.getSaves(), visitor.getSaves()));

                matchStatsListAdapter.notifyDataSetChanged();
            }
        }


        if (matchDetails.getLocalTeamStanding() != null) {
            standings.setText("Standings: "+matchDetails.getLeagueName());
            TeamStanding localTeamStanding = matchDetails.getLocalTeamStanding();
            localTeamRank.setText(localTeamStanding.getPosition());
            localTeamName.setText(localTeamStanding.getTeam());
            localTeamGp.setText(localTeamStanding.getTotalPlayed());
            localTeamWins.setText(localTeamStanding.getTotalWon());
            localTeamDraws.setText(localTeamStanding.getTotalDraw());
            localTeamLoss.setText(localTeamStanding.getTotalLost());
            localTeamGd.setText(localTeamStanding.getGoalDifference());
            localTeamPts.setText(localTeamStanding.getPoints());
        } else {
            cardView.setVisibility(View.GONE);
        }

        if (matchDetails.getVisitorTeamStanding() != null) {
            TeamStanding localTeamStanding = matchDetails.getVisitorTeamStanding();
            visitorTeamRank.setText(localTeamStanding.getPosition());
            visitorTeamName.setText(localTeamStanding.getTeam());
            visitorTeamGp.setText(localTeamStanding.getTotalPlayed());
            visitorTeamWins.setText(localTeamStanding.getTotalWon());
            visitorTeamDraws.setText(localTeamStanding.getTotalDraw());
            visitorTeamLoss.setText(localTeamStanding.getTotalLost());
            visitorTeamGd.setText(localTeamStanding.getGoalDifference());
            visitorTeamPts.setText(localTeamStanding.getPoints());
        }

        if (matchDetails.getStats() != null) {
            localWins.setText(matchDetails.getStats().getTotal_localteam_won());
            visitorWins.setText(matchDetails.getStats().getTotal_visitorteam_won());
            localGoals.setText(matchDetails.getStats().getTotal_localteam_scored());
            visitorGoals.setText(matchDetails.getStats().getTotal_visitorteam_scored());
        } else {
            cardHead2Head.setVisibility(View.GONE);
        }

    }

    private void startPlayerProfileActivity(String playerId) {
        if (!playerId.equals("")) {
            Intent intent = new Intent(getContext(), PlayerInfoActivity.class);
            intent.putExtra("playerId", playerId);
            getActivity().startActivity(intent);
        }

    }

    private static class MatchEventViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout leftLinearLayout;
        protected LinearLayout rightLinearLayout;
        protected CircleImageView leftPlayer;
        protected TextView leftPlayerName;
        protected CircleImageView leftEvent;
        protected CircleImageView rightPlayer;
        protected TextView rightPlayerName;
        protected CircleImageView rightEvent;
        protected TextView minute;

        public MatchEventViewHolder(View itemView) {
            super(itemView);

            leftLinearLayout = ViewHolder.get(itemView, R.id.left_linear_layout);
            rightLinearLayout = ViewHolder.get(itemView, R.id.right_linear_layout);
            leftPlayer = ViewHolder.get(itemView, R.id.img_left_player);
            leftPlayerName = ViewHolder.get(itemView, R.id.tv_left_player);
            leftEvent = ViewHolder.get(itemView, R.id.img_left_event);
            rightPlayer = ViewHolder.get(itemView, R.id.img_right_player);
            rightPlayerName = ViewHolder.get(itemView, R.id.tv_right_player);
            rightEvent = ViewHolder.get(itemView, R.id.img_right_event);
            minute = ViewHolder.get(itemView, R.id.tv_minute);
        }
    }

    private static class StatsViewHolder extends RecyclerView.ViewHolder {
        protected TextView localTeamScore;
        protected TextView criteria;
        protected TextView visitorTeamScore;

        public StatsViewHolder(View itemView) {
            super(itemView);
            localTeamScore = ViewHolder.get(itemView, R.id.tv_local_team_score);
            criteria = ViewHolder.get(itemView, R.id.tv_criteria);
            visitorTeamScore = ViewHolder.get(itemView, R.id.tv_visitor_team_score);
        }
    }

    private static class TickerViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView commentry;

        public TickerViewHolder(View itemView) {
            super(itemView);
            time = ViewHolder.get(itemView, R.id.tv_commentry_minute);
            commentry = ViewHolder.get(itemView, R.id.tv_commentry);
        }
    }


    private class StatModel {
        private String criteria;
        private String localTeamScore;
        private String visitorTeamScore;

        public StatModel(String criteria, String localTeamScore, String visitorTeamScore) {
            this.criteria = criteria;
            this.localTeamScore = localTeamScore;
            this.visitorTeamScore = visitorTeamScore;
        }

        public String getCriteria() {
            return criteria;
        }

        public void setCriteria(String criteria) {
            this.criteria = criteria;
        }

        public String getLocalTeamScore() {
            return localTeamScore;
        }

        public void setLocalTeamScore(String localTeamScore) {
            this.localTeamScore = localTeamScore;
        }

        public String getVisitorTeamScore() {
            return visitorTeamScore;
        }

        public void setVisitorTeamScore(String visitorTeamScore) {
            this.visitorTeamScore = visitorTeamScore;
        }

        @Override
        public String toString() {
            return "StatModel{" +
                    "criteria='" + criteria + '\'' +
                    ", localTeamScore='" + localTeamScore + '\'' +
                    ", visitorTeamScore='" + visitorTeamScore + '\'' +
                    '}';
        }
    }
}
