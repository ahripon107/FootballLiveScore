package com.sfuronlabs.livescore.football.fragments;

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
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.Team;
import com.sfuronlabs.livescore.football.model.TransferPlayer;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class TransferFragment extends RoboFragment{

    @InjectView(R.id.list)
    private RecyclerView transferList;

    @InjectView(R.id.tv_empty)
    private TextView emptyView;

    @Inject
    private ArrayList<TransferPlayer> transferPlayers;

    private BasicListAdapter<TransferPlayer, TransferListViewHolder> transferListAdapter;
    private Team team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transferListAdapter = new BasicListAdapter<TransferPlayer, TransferListViewHolder>(transferPlayers) {
            @Override
            public TransferListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transfer,parent,false);
                return new TransferListViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TransferListViewHolder holder, int position) {
                TransferPlayer transferPlayer = transferPlayers.get(position);
                holder.name.setText(transferPlayer.getName());
                holder.description.setText(transferPlayer.getDate());
                holder.price.setText(transferPlayer.getType());
                if (transferPlayer.getTo() == null) {
                    holder.toTeam.setText(transferPlayer.getFrom());
                    holder.inOut.setImageDrawable(getResources().getDrawable(R.drawable.in));
                } else {
                    holder.toTeam.setText(transferPlayer.getTo());
                    holder.inOut.setImageDrawable(getResources().getDrawable(R.drawable.out));
                }

                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/playerimages/"+transferPlayer.getId()+".png")
                        .into(holder.imageView);

            }
        };

        transferList.setAdapter(transferListAdapter);
        transferList.setLayoutManager(new LinearLayoutManager(getContext()));

        team = (Team) getArguments().getSerializable("teamDetails");
        if (team.getTransfers() != null) {
            transferPlayers.addAll(team.getTransfers().getIn());
            transferPlayers.addAll(team.getTransfers().getOut());
            transferListAdapter.notifyDataSetChanged();
        }

        emptyView.setVisibility(View.GONE);
        if (transferPlayers.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private static class TransferListViewHolder extends RecyclerView.ViewHolder {

        protected CircleImageView imageView;
        protected TextView name;
        protected TextView description;
        protected CardView cardView;
        protected TextView toTeam;
        protected TextView price;
        protected ImageView inOut;

        public TransferListViewHolder(View itemView) {
            super(itemView);

            imageView = ViewHolder.get(itemView, R.id.image);
            name = ViewHolder.get(itemView, R.id.tv_name);
            description = ViewHolder.get(itemView, R.id.tv_description);
            cardView = ViewHolder.get(itemView, R.id.squal_player_card);
            toTeam = ViewHolder.get(itemView, R.id.tv_to_team);
            price = ViewHolder.get(itemView, R.id.tv_price);
            inOut = ViewHolder.get(itemView, R.id.in_out_image);
        }
    }
}
