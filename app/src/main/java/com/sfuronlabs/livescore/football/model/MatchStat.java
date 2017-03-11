package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchStat implements Serializable{
    private MatchTeamStat localTeamStat;
    private MatchTeamStat visitorTeamStat;

    @JsonProperty("localteam")
    public MatchTeamStat getLocalTeamStat() {
        return localTeamStat;
    }

    public void setLocalTeamStat(MatchTeamStat localTeamStat) {
        this.localTeamStat = localTeamStat;
    }

    @JsonProperty("visitorteam")
    public MatchTeamStat getVisitorTeamStat() {
        return visitorTeamStat;
    }

    public void setVisitorTeamStat(MatchTeamStat visitorTeamStat) {
        this.visitorTeamStat = visitorTeamStat;
    }

    @Override
    public String toString() {
        return "MatchStat{" +
                "localTeamStat=" + localTeamStat.toString() +
                ", visitorTeamStat=" + visitorTeamStat.toString() +
                '}';
    }
}
