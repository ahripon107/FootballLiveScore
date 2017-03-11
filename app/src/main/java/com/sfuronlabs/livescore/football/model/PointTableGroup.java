package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointTableGroup implements Serializable{
    private String group;
    private String round;
    private String stageid;
    private String leaugeid;
    private String season;
    private String league;
    private String country;

    private List<TeamStanding> teams;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStageid() {
        return stageid;
    }

    public void setStageid(String stageid) {
        this.stageid = stageid;
    }

    public String getLeaugeid() {
        return leaugeid;
    }

    public void setLeaugeid(String leaugeid) {
        this.leaugeid = leaugeid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<TeamStanding> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamStanding> teams) {
        this.teams = teams;
    }
}
