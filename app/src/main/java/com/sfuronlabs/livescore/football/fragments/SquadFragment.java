package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.PlayerInfoActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.model.TeamSquadPlayer;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class SquadFragment extends RoboFragment{

    @InjectView(R.id.list)
    private RecyclerView squadList;

    @Inject
    private ArrayList<TeamSquadPlayer> players;

    private BasicListAdapter<TeamSquadPlayer, SquadViewHolder> squadListAdapter;

    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        team = (Team) getArguments().getSerializable("teamDetails");

        squadListAdapter = new BasicListAdapter<TeamSquadPlayer, SquadViewHolder>(players) {
            @Override
            public SquadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_team_squad,parent,false);
                return new SquadViewHolder(view);
            }

            @Override
            public void onBindViewHolder(SquadViewHolder holder, int position) {
                final TeamSquadPlayer player = players.get(position);
                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/"+player.getId()+".png")
                        .into(holder.imageView);

                holder.name.setText(player.getName());
                if (player.getPosition().equals("G")) {
                    holder.description.setText("Goalkeeper");
                }
                else if (player.getPosition().equals("D")) {
                    holder.description.setText("Defender");
                }
                else if (player.getPosition().equals("M")) {
                    holder.description.setText("Midfielder");
                }
                else if (player.getPosition().equals("A")) {
                    holder.description.setText("Attacker");
                }

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), PlayerInfoActivity.class);
                        intent.putExtra("playerId", player.getId());
                        getActivity().startActivity(intent);
                    }
                });

            }
        };

        squadList.setAdapter(squadListAdapter);
        squadList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (team.getSquad() != null) {
            players.addAll(team.getSquad());
            squadListAdapter.notifyDataSetChanged();
        }


    }

    private static class SquadViewHolder extends RecyclerView.ViewHolder {
        protected CircleImageView imageView;
        protected TextView name;
        protected TextView description;
        protected CardView cardView;


        public SquadViewHolder(View itemView) {
            super(itemView);

            imageView = ViewHolder.get(itemView, R.id.image);
            name = ViewHolder.get(itemView, R.id.tv_name);
            description = ViewHolder.get(itemView, R.id.tv_description);
            cardView = ViewHolder.get(itemView, R.id.squal_player_card);

        }
    }
}
