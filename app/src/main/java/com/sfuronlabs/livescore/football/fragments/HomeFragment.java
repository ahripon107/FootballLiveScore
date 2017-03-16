package com.sfuronlabs.livescore.football.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.LeagueListActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.CountryAppStart;
import com.sfuronlabs.livescore.football.model.FeedAppStart;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.DividerItemDecoration;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class HomeFragment extends RoboFragment{

    @InjectView(R.id.list)
    private RecyclerView countryList;

    @Inject
    private ArrayList<CountryAppStart> countries;

    private BasicListAdapter<CountryAppStart, CountryListViewHolder> countryListAdapter;

    @Inject
    private FeedAppStart feedAppStart;

    @Inject
    private NetworkService networkService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countryListAdapter = new BasicListAdapter<CountryAppStart, CountryListViewHolder>(countries) {
            @Override
            public CountryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_country, parent, false);
                return new CountryListViewHolder(view);
            }

            @Override
            public void onBindViewHolder(CountryListViewHolder holder, final int position) {
                holder.countryName.setText(countries.get(position).getCountry());
                Picasso.with(getContext()).load("http://static.holoduke.nl/footapi/images/flags/"+prepareCountryName(countries.get(position).getCountry())+".png").into(holder.countryImage);

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), LeagueListActivity.class);
                        intent.putExtra("country", countries.get(position));
                        getActivity().startActivity(intent);
                    }
                });
            }
        };



        networkService.fetchFeedAppStart(new DefaultMessageHandler(getContext(),true) {
            @Override
            public void onSuccess(Message msg) {
                feedAppStart = (FeedAppStart) msg.obj;
                countries.clear();
                countries.addAll(feedAppStart.getCountries());
                countryListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        countryList.setAdapter(countryListAdapter);
        countryList.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        countryList.addItemDecoration(itemDecoration);
    }

    private static class CountryListViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout linearLayout;
        protected ImageView countryImage;
        protected TextView countryName;

        public CountryListViewHolder(View itemView) {
            super(itemView);
            countryImage = ViewHolder.get(itemView, R.id.img_team);
            countryName = ViewHolder.get(itemView, R.id.tv_team_name);
            linearLayout = ViewHolder.get(itemView, R.id.country_layout);
        }
    }

    private String prepareCountryName(String str) {
        return str.replace(' ', '-').toLowerCase();
    }
}
