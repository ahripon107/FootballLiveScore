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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.activity.NewsDetailsActivity;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.News;
import com.sfuronlabs.livescore.football.model.NewsItem;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.Constants;
import com.sfuronlabs.livescore.football.util.DividerItemDecoration;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */

public class NewsFragment extends RoboFragment{

    @InjectView(R.id.list)
    private RecyclerView newsList;

    @Inject
    private ArrayList<NewsItem> newsItems;

    @Inject
    private NetworkService networkService;

    private News news;

    private BasicListAdapter<NewsItem, NewsViewHolder> newsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsListAdapter = new BasicListAdapter<NewsItem, NewsViewHolder>(newsItems) {
            @Override
            public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,parent,false);
                return new NewsViewHolder(view);
            }

            @Override
            public void onBindViewHolder(NewsViewHolder holder, int position) {
                final NewsItem item = newsItems.get(position);
                if (Constants.showImage) {
                    Picasso.with(getContext()).load(item.getImage()).resize(50,50).placeholder(R.drawable.news_visual_voetbalnieuws).into(holder.image);
                }

                holder.title.setText(item.getTitle());
                holder.date.setText(item.getDate());

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
                        intent.putExtra(NewsDetailsActivity.EXTRA_URL, item.getLink());
                        getActivity().startActivity(intent);
                    }
                });
            }
        };

        networkService.fetchNewsList(new DefaultMessageHandler(getContext(),true){
            @Override
            public void onSuccess(Message msg) {
                news = (News) msg.obj;
                newsItems.addAll(news.getItems());
                newsListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsList.setAdapter(newsListAdapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        newsList.addItemDecoration(itemDecoration);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list,container,false);
    }


    private static class NewsViewHolder extends RecyclerView.ViewHolder {
        protected CircleImageView image;
        protected TextView title;
        protected TextView date;
        protected LinearLayout linearLayout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            image = ViewHolder.get(itemView, R.id.img_news);
            title = ViewHolder.get(itemView, R.id.tv_title);
            date = ViewHolder.get(itemView, R.id.tv_date);
            linearLayout = ViewHolder.get(itemView, R.id.linear_layout);
        }
    }
}
