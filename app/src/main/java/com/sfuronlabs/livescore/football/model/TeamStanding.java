package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamStanding implements Serializable{
    private String teamId;
    private String position;
    private String team;
    private String totalPlayed;
    private String totalWon;
    private String totalDraw;
    private String totalLost;
    private String totalGoalsFor;
    private String totalGoalsAgainst;
    private String goalDifference;
    private String points;
    private String description;

    @JsonProperty("id_gs")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @JsonProperty("matchPoints")
    public String getTotalPlayed() {
        return totalPlayed;
    }

    public void setTotalPlayed(String totalPlayed) {
        this.totalPlayed = totalPlayed;
    }

    public String getTotalWon() {
        return totalWon;
    }

    public void setTotalWon(String totalWon) {
        this.totalWon = totalWon;
    }

    public String getTotalDraw() {
        return totalDraw;
    }

    public void setTotalDraw(String totalDraw) {
        this.totalDraw = totalDraw;
    }

    public String getTotalLost() {
        return totalLost;
    }

    public void setTotalLost(String totalLost) {
        this.totalLost = totalLost;
    }

    public String getTotalGoalsFor() {
        return totalGoalsFor;
    }

    public void setTotalGoalsFor(String totalGoalsFor) {
        this.totalGoalsFor = totalGoalsFor;
    }

    public String getTotalGoalsAgainst() {
        return totalGoalsAgainst;
    }

    public void setTotalGoalsAgainst(String totalGoalsAgainst) {
        this.totalGoalsAgainst = totalGoalsAgainst;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(String goalDifference) {
        this.goalDifference = goalDifference;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TeamStanding{" +
                "teamId='" + teamId + '\'' +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                ", totalPlayed='" + totalPlayed + '\'' +
                ", totalWon='" + totalWon + '\'' +
                ", totalDraw='" + totalDraw + '\'' +
                ", totalLost='" + totalLost + '\'' +
                ", totalGoalsFor='" + totalGoalsFor + '\'' +
                ", totalGoalsAgainst='" + totalGoalsAgainst + '\'' +
                ", goalDifference='" + goalDifference + '\'' +
                ", points='" + points + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
