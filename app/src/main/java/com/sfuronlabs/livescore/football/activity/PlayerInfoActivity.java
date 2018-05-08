package com.sfuronlabs.livescore.football.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.inject.Inject;
import com.sfuronlabs.livescore.football.R;
import com.sfuronlabs.livescore.football.adapter.BasicListAdapter;
import com.sfuronlabs.livescore.football.model.Player;
import com.sfuronlabs.livescore.football.model.Statistic;
import com.sfuronlabs.livescore.football.service.DefaultMessageHandler;
import com.sfuronlabs.livescore.football.service.NetworkService;
import com.sfuronlabs.livescore.football.util.Constants;
import com.sfuronlabs.livescore.football.util.RoboAppCompatActivity;
import com.sfuronlabs.livescore.football.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Ripon
 */
@ContentView(R.layout.activity_player_info)
public class PlayerInfoActivity extends RoboAppCompatActivity {

    @InjectView(R.id.player_stat_list)
    private RecyclerView playerStatList;

    @InjectView(R.id.tv_player_age)
    private TextView age;

    @InjectView(R.id.tv_player_nationality)
    private TextView nationality;

    @InjectView(R.id.tv_player_birthplace)
    private TextView birthPlace;

    @InjectView(R.id.tv_player_position)
    private TextView position;

    @InjectView(R.id.tv_player_team)
    private TextView team;

    @InjectView(R.id.tv_player_weight)
    private TextView weight;

    @InjectView(R.id.tv_player_height)
    private TextView height;

    @InjectView(R.id.img_player)
    private ImageView playerImage;

    @InjectView(R.id.adview_player_details)
    private AdView adView;

    private BasicListAdapter<Statistic, PlayerStatViewHolder> playerStatListAdapter;

    @Inject
    private ArrayList<Statistic> playerStats;

    @Inject
    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        playerStatListAdapter = new BasicListAdapter<Statistic, PlayerStatViewHolder>(playerStats) {
            @Override
            public PlayerStatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_stat_list_item, parent, false);
                return new PlayerStatViewHolder(view);
            }

            @Override
            public void onBindViewHolder(PlayerStatViewHolder holder, int position) {
                final Statistic statistic = playerStats.get(position);
                holder.season.setText(statistic.getSeason());
                holder.goals.setText(statistic.getGoals());
                holder.yellowCards.setText(statistic.getYellowCards());
                holder.redCards.setText(statistic.getRedCards());
                holder.time.setText(statistic.getMinutes());
                holder.subIn.setText(statistic.getSubstituteIn());
                holder.subOut.setText(statistic.getSubstituteOut());
                holder.lineups.setText(statistic.getLineups());
                holder.sidelined.setText(statistic.getSubstitutesOnBench());
                holder.country.setText(statistic.getName());
                holder.leagueName.setText(statistic.getLeague());

                Picasso.with(PlayerInfoActivity.this).load("http://static.holoduke.nl/footapi/images/teams_gs/" +
                        statistic.getTeamId() + "_small.png").into(holder.teamImage);

                holder.playerStatCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(PlayerInfoActivity.this, LeagueDetailsActivity.class);
//                        intent.putExtra("leagueKey", statistic.getL);
                    }
                });
            }
        };

        playerStatList.setAdapter(playerStatListAdapter);
        playerStatList.setLayoutManager(new LinearLayoutManager(this));

        Log.d("ripon", "http://static.holoduke.nl/footapi/players/" + getIntent().getStringExtra("playerId") + ".json");

        networkService.fetchPlayerCareer(getIntent().getStringExtra("playerId"), new DefaultMessageHandler(this, true) {
            @Override
            public void onSuccess(Message msg) {
                Player player = (Player) msg.obj;
                Log.d("ripon", player.toString());

                setTitle(player.getName());

                setBasicInfo(age, "Age", player.getAge());
                setBasicInfo(nationality, "Nationality", player.getNationality());
                setBasicInfo(birthPlace, "Birth Place", player.getBirthPlace());
                setBasicInfo(position, "Position", player.getPosition());
                setBasicInfo(team, "Team", player.getTeam());
                setBasicInfo(weight, "Weight", player.getWeight());
                setBasicInfo(height, "Height", player.getHeight());

                Picasso.with(PlayerInfoActivity.this).load("http://static.holoduke.nl/footapi/images/playerimages/" + player.getId() + ".png")
                        .into(playerImage);

                playerStats.addAll(player.getStatistics());
                playerStats.addAll(player.getStatisticsCups());
                playerStats.addAll(player.getStatisticsCupsIntl());
                playerStats.addAll(player.getStatisticsIntl());

                playerStatListAdapter.notifyDataSetChanged();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(Constants.ONE_PLUS_TEST_DEVICE)
                .addTestDevice(Constants.XIAOMI_TEST_DEVICE).build();
        adView.loadAd(adRequest);

    }

    private void setBasicInfo(TextView textView, String property, String value) {
        if (value != null) {
            textView.setText(Html.fromHtml("<b>" + property + ":</b> " + value));
        } else {
            textView.setText(Html.fromHtml("<b>" + property + ":</b> "));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class PlayerStatViewHolder extends RecyclerView.ViewHolder {
        protected TextView season;
        protected TextView goals;
        protected TextView yellowCards;
        protected TextView redCards;
        protected TextView time;
        protected TextView subIn;
        protected TextView subOut;
        protected TextView lineups;
        protected TextView sidelined;
        protected ImageView teamImage;
        protected TextView country;
        protected TextView leagueName;
        protected CardView playerStatCard;

        public PlayerStatViewHolder(View itemView) {
            super(itemView);
            season = ViewHolder.get(itemView, R.id.tv_season);
            goals = ViewHolder.get(itemView, R.id.tv_goals);
            yellowCards = ViewHolder.get(itemView, R.id.tv_yellowCards);
            redCards = ViewHolder.get(itemView, R.id.tv_redCards);
            time = ViewHolder.get(itemView, R.id.tv_time);
            subIn = ViewHolder.get(itemView, R.id.tv_sub_in);
            subOut = ViewHolder.get(itemView, R.id.tv_sub_out);
            lineups = ViewHolder.get(itemView, R.id.tv_lineup);
            sidelined = ViewHolder.get(itemView, R.id.tv_sidelined);
            teamImage = ViewHolder.get(itemView, R.id.img_team);
            country = ViewHolder.get(itemView, R.id.tv_country);
            leagueName = ViewHolder.get(itemView, R.id.tv_league_name);
            playerStatCard = ViewHolder.get(itemView, R.id.player_stat_card);
        }
    }
}
