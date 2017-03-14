package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueAppStart implements Serializable{
    private String leagueName;
    private String key;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LeagueAppStart{" +
                "leagueName='" + leagueName + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
