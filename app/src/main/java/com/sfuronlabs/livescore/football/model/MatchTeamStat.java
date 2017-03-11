package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchTeamStat implements Serializable{
    private String totalShots;
    private String shotsToGoal;
    private String fouls;
    private String corners;
    private String offsides;
    private String possessationTime;
    private String yellowCards;
    private String redCards;
    private String saves;

    @JsonProperty("shotstotal")
    public String getTotalShots() {
        return totalShots;
    }

    public void setTotalShots(String totalShots) {
        this.totalShots = totalShots;
    }

    @JsonProperty("shotsgoal")
    public String getShotsToGoal() {
        return shotsToGoal;
    }

    public void setShotsToGoal(String shotsToGoal) {
        this.shotsToGoal = shotsToGoal;
    }

    public String getFouls() {
        return fouls;
    }

    public void setFouls(String fouls) {
        this.fouls = fouls;
    }

    public String getCorners() {
        return corners;
    }

    public void setCorners(String corners) {
        this.corners = corners;
    }

    public String getOffsides() {
        return offsides;
    }

    public void setOffsides(String offsides) {
        this.offsides = offsides;
    }

    @JsonProperty("possestiontime")
    public String getPossessationTime() {
        return possessationTime;
    }

    public void setPossessationTime(String possessationTime) {
        this.possessationTime = possessationTime;
    }

    @JsonProperty("yellowcards")
    public String getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(String yellowCards) {
        this.yellowCards = yellowCards;
    }

    @JsonProperty("redcards")
    public String getRedCards() {
        return redCards;
    }

    public void setRedCards(String redCards) {
        this.redCards = redCards;
    }

    public String getSaves() {
        return saves;
    }

    public void setSaves(String saves) {
        this.saves = saves;
    }
}
