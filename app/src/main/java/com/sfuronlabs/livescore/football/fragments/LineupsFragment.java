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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.PlayerInfoActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.Commentary;
import com.sfuronlabs.livescore.football.model.MatchDetails;
import com.sfuronlabs.livescore.football.model.MatchEvent;
import com.sfuronlabs.livescore.football.model.MatchLineup;
import com.sfuronlabs.livescore.football.model.MatchLineupPlayer;
import com.sfuronlabs.livescore.football.model.MatchSubstitutionPlayer;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class LineupsFragment extends RoboFragment {
    @InjectView(R.id.team_lineup_list)
    private RecyclerView lineupsList;

    @InjectView(R.id.tv_empty_view)
    private TextView emptyView;

//    @InjectView(R.id.substitutions_list)
//    private RecyclerView substitutionsList;

    private MatchDetails matchDetails;

    @Inject
    private ArrayList<MatchLineupPlayer> homeTeam;

    @Inject
    private ArrayList<MatchLineupPlayer> visitorTeam;

//    @Inject
//    private ArrayList<MatchSubstitutionPlayer> matchSubstitutionPlayers;

    @Inject
    private NetworkService networkService;

    private BasicListAdapter<MatchLineupPlayer, LineupViewHolder> lineupsListAdapter;
    private BasicListAdapter<MatchSubstitutionPlayer, SubstitutionListViewHolder> substitutionListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lineups_fragments, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        matchDetails = (MatchDetails) getArguments().getSerializable("matchdetails");

        lineupsListAdapter = new BasicListAdapter<MatchLineupPlayer, LineupViewHolder>(homeTeam, visitorTeam) {
            @Override
            public LineupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_event_list_item, parent, false);
                return new LineupViewHolder(view);
            }

            @Override
            public void onBindViewHolder(LineupViewHolder holder, int position) {
                final MatchLineupPlayer homeTeamPlayer = homeTeam.get(position);
                final MatchLineupPlayer visitorTeamPlayer = visitorTeam.get(position);

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
                        homeTeamPlayer.getId() + "_small.png").into(holder.leftPlayer);
                holder.leftPlayerName.setText(homeTeamPlayer.getName());
                holder.minute.setVisibility(View.GONE);
                holder.leftEvent.setVisibility(View.GONE);


                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
                        visitorTeamPlayer.getId() + "_small.png").into(holder.rightPlayer);
                holder.rightPlayerName.setText(visitorTeamPlayer.getName());
                holder.rightEvent.setVisibility(View.GONE);

                holder.leftLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPlayerProfileActivity(homeTeamPlayer.getId());
                    }
                });

                holder.rightLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPlayerProfileActivity(visitorTeamPlayer.getId());
                    }
                });

            }
        };

        lineupsList.setAdapter(lineupsListAdapter);
        lineupsList.setLayoutManager(new LinearLayoutManager(getContext()));

//        substitutionListAdapter = new BasicListAdapter<MatchSubstitutionPlayer, SubstitutionListViewHolder>(matchSubstitutionPlayers) {
//            @Override
//            public SubstitutionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_substitutions,parent,false);
//                return new SubstitutionListViewHolder(view);
//            }
//
//            @Override
//            public void onBindViewHolder(SubstitutionListViewHolder holder, int position) {
//                MatchSubstitutionPlayer matchSubstitutionPlayer = matchSubstitutionPlayers.get(position);
//
//                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
//                        matchSubstitutionPlayer.getOn_id() + "_small.png").into(holder.leftInPlayer);
//                holder.leftInPlayerName.setText(matchSubstitutionPlayer.getOn());
//                holder.leftInEvent.setImageDrawable(getResources().getDrawable(R.drawable.subst_in));
//
//                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
//                        matchSubstitutionPlayer.getOff_id() + "_small.png").into(holder.leftOutEvent);
//                holder.leftOutPlayerName.setText(matchSubstitutionPlayer.getOn());
//                holder.leftOutEvent.setImageDrawable(getResources().getDrawable(R.drawable.subst_out));
//
//                holder.rightLinearLayout.setVisibility(View.GONE);
//
//                holder.minute.setText(matchSubstitutionPlayer.getMinute());
//
//
//                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
//                        matchSubstitutionPlayer.getOn_id() + "_small.png").into(holder.rightInPlayer);
//                holder.rightInPlayerName.setText(matchSubstitutionPlayer.getOn());
//                holder.rightInEvent.setImageDrawable(getResources().getDrawable(R.drawable.subst_in));
//
//                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/" +
//                        matchSubstitutionPlayer.getOff_id() + "_small.png").into(holder.rightOutPlayer);
//                holder.rightOutPlayerName.setText(matchSubstitutionPlayer.getOff());
//                holder.rightOutEvent.setImageDrawable(getResources().getDrawable(R.drawable.subst_out));
//            }
//        };
//
//        substitutionsList.setAdapter(substitutionListAdapter);
//        substitutionsList.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("ripon", "http://holoduke.nl/footapi/commentaries/"+matchDetails.getId()+".json");

        networkService.fetchCommentry(matchDetails.getId(), new DefaultMessageHandler(getContext(), false){
            @Override
            public void onSuccess(Message msg) {
                Commentary commentry = (Commentary) msg.obj;
                Log.d("riponc", commentry.toString());

                if (commentry.getTeams() != null) {
                    homeTeam.clear();
                    visitorTeam.clear();

                    homeTeam.addAll(commentry.getTeams().getLocalteam());
                    visitorTeam.addAll(commentry.getTeams().getVisitorteam());

                    lineupsListAdapter.notifyDataSetChanged();
                } else if (matchDetails.getLineups() != null) {
                    homeTeam.clear();
                    visitorTeam.clear();

                    homeTeam.addAll(matchDetails.getLineups().getLocalteam());
                    visitorTeam.addAll(matchDetails.getLineups().getVisitorteam());

                    lineupsListAdapter.notifyDataSetChanged();
                }

                emptyView.setVisibility(View.GONE);
                if (homeTeam.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }

//                if (commentry.getSubstitutions() != null) {
//                    matchSubstitutionPlayers.clear();
//                    matchSubstitutionPlayers.addAll(commentry.getSubstitutions().getLocalteam());
//                    matchSubstitutionPlayers.addAll(commentry.getSubstitutions().getVisitorteam());
//
//                    Collections.sort(matchSubstitutionPlayers);
//
//                    substitutionListAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onFailure(Message msg) {
                emptyView.setVisibility(View.GONE);
                if (homeTeam.size() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void startPlayerProfileActivity(String playerId) {
        if (!playerId.equals("")) {
            Intent intent = new Intent(getContext(), PlayerInfoActivity.class);
            intent.putExtra("playerId", playerId);
            getActivity().startActivity(intent);
        }

    }

    private static class LineupViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout leftLinearLayout;
        protected LinearLayout rightLinearLayout;
        protected CircleImageView leftPlayer;
        protected TextView leftPlayerName;
        protected CircleImageView leftEvent;
        protected CircleImageView rightPlayer;
        protected TextView rightPlayerName;
        protected CircleImageView rightEvent;
        protected TextView minute;

        public LineupViewHolder(View itemView) {
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

    private static class SubstitutionListViewHolder extends RecyclerView.ViewHolder {

        protected LinearLayout leftLinearLayout;
        protected LinearLayout rightLinearLayout;
        protected CircleImageView leftInPlayer;
        protected TextView leftInPlayerName;
        protected CircleImageView leftInEvent;
        protected CircleImageView rightInPlayer;
        protected TextView rightInPlayerName;
        protected CircleImageView rightInEvent;

        protected CircleImageView leftOutPlayer;
        protected TextView leftOutPlayerName;
        protected CircleImageView leftOutEvent;
        protected CircleImageView rightOutPlayer;
        protected TextView rightOutPlayerName;
        protected CircleImageView rightOutEvent;

        protected TextView minute;

        public SubstitutionListViewHolder(View itemView) {
            super(itemView);

            leftLinearLayout = ViewHolder.get(itemView, R.id.left_linear_layout);
            rightLinearLayout = ViewHolder.get(itemView, R.id.right_linear_layout);
            leftInPlayer = ViewHolder.get(itemView, R.id.img_left_in_event);
            leftInPlayerName = ViewHolder.get(itemView, R.id.tv_left_in_player);
            leftInEvent = ViewHolder.get(itemView, R.id.img_left_in_event);
            rightInPlayer = ViewHolder.get(itemView, R.id.img_right_in_player);
            rightInPlayerName = ViewHolder.get(itemView, R.id.tv_right_in_player);
            rightInEvent = ViewHolder.get(itemView, R.id.img_right_in_event);

            leftOutPlayer = ViewHolder.get(itemView, R.id.img_left_out_player);
            leftOutPlayerName = ViewHolder.get(itemView, R.id.tv_left_out_player);
            leftOutEvent = ViewHolder.get(itemView, R.id.img_left_out_event);
            rightOutPlayer = ViewHolder.get(itemView, R.id.img_right_out_player);
            rightOutPlayerName = ViewHolder.get(itemView, R.id.tv_right_out_player);
            rightOutEvent = ViewHolder.get(itemView, R.id.img_right_out_event);

            minute = ViewHolder.get(itemView, R.id.tv_minute);
        }
    }
}
