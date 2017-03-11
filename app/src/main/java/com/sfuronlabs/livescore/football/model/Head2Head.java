package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Head2Head implements Serializable{
    private String total_localteam_won;
    private String total_visitorteam_won;
    private String total_draws;
    private String total_localteam_scored;
    private String total_visitorteam_scored;

    public String getTotal_localteam_won() {
        return total_localteam_won;
    }

    public void setTotal_localteam_won(String total_localteam_won) {
        this.total_localteam_won = total_localteam_won;
    }

    public String getTotal_visitorteam_won() {
        return total_visitorteam_won;
    }

    public void setTotal_visitorteam_won(String total_visitorteam_won) {
        this.total_visitorteam_won = total_visitorteam_won;
    }

    public String getTotal_draws() {
        return total_draws;
    }

    public void setTotal_draws(String total_draws) {
        this.total_draws = total_draws;
    }

    public String getTotal_localteam_scored() {
        return total_localteam_scored;
    }

    public void setTotal_localteam_scored(String total_localteam_scored) {
        this.total_localteam_scored = total_localteam_scored;
    }

    public String getTotal_visitorteam_scored() {
        return total_visitorteam_scored;
    }

    public void setTotal_visitorteam_scored(String total_visitorteam_scored) {
        this.total_visitorteam_scored = total_visitorteam_scored;
    }

    @Override
    public String toString() {
        return "Head2Head{" +
                "total_localteam_won='" + total_localteam_won + '\'' +
                ", total_visitorteam_won='" + total_visitorteam_won + '\'' +
                ", total_draws='" + total_draws + '\'' +
                ", total_localteam_scored='" + total_localteam_scored + '\'' +
                ", total_visitorteam_scored='" + total_visitorteam_scored + '\'' +
                '}';
    }
}
