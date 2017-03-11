package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistic implements Serializable{
    private String name;
    private String teamId;
    private String league;
    private String leagueId;
    private String season;
    private String minutes;
    private String appearences;
    private String lineups;
    private String substituteIn;
    private String substituteOut;
    private String substitutesOnBench;
    private String goals;
    private String yellowCards;
    private String yellowRed;
    private String redCards;

    public String getName() {
        return name;
    }

    @JsonProperty("teamid")
    public String getTeamId() {
        return teamId;
    }

    public String getLeague() {
        return league;
    }

    @JsonProperty("leagueid")
    public String getLeagueId() {
        return leagueId;
    }

    public String getSeason() {
        return season;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getAppearences() {
        return appearences;
    }

    public String getLineups() {
        return lineups;
    }

    @JsonProperty("substitute_in")
    public String getSubstituteIn() {
        return substituteIn;
    }

    @JsonProperty("substitute_out")
    public String getSubstituteOut() {
        return substituteOut;
    }

    @JsonProperty("substitutes_on_bench")
    public String getSubstitutesOnBench() {
        return substitutesOnBench;
    }

    public String getGoals() {
        return goals;
    }

    @JsonProperty("yellowcards")
    public String getYellowCards() {
        return yellowCards;
    }

    @JsonProperty("yellowred")
    public String getYellowRed() {
        return yellowRed;
    }

    @JsonProperty("redcards")
    public String getRedCards() {
        return redCards;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "name='" + name + '\'' +
                ", teamId='" + teamId + '\'' +
                ", league='" + league + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", season='" + season + '\'' +
                ", minutes='" + minutes + '\'' +
                ", appearences='" + appearences + '\'' +
                ", lineups='" + lineups + '\'' +
                ", substituteIn='" + substituteIn + '\'' +
                ", substituteOut='" + substituteOut + '\'' +
                ", substitutesOnBench='" + substitutesOnBench + '\'' +
                ", goals='" + goals + '\'' +
                ", yellowCards='" + yellowCards + '\'' +
                ", yellowRed='" + yellowRed + '\'' +
                ", redCards='" + redCards + '\'' +
                '}';
    }
}
