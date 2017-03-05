package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamLeague {
    private String country;
    private String league;
    private String key;
    private String position;
    private String round;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "TeamLeague{" +
                "country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", key='" + key + '\'' +
                ", position='" + position + '\'' +
                ", round='" + round + '\'' +
                '}';
    }
}
