package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commentary implements Serializable {
    private List<LiveTicker> liveticker;
    private Substitutions substitutions;
    private MatchLineup teams;

    public List<LiveTicker> getLiveticker() {
        return liveticker;
    }

    public void setLiveticker(List<LiveTicker> liveticker) {
        this.liveticker = liveticker;
    }

    public Substitutions getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(Substitutions substitutions) {
        this.substitutions = substitutions;
    }

    public MatchLineup getTeams() {
        return teams;
    }

    public void setTeams(MatchLineup teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        StringBuilder tkr = new StringBuilder();
        String sbs = "";
        if (liveticker != null) {
            for (int i=0;i<liveticker.size();i++) {
                tkr.append(liveticker.get(i).toString());
            }
        }

        if (substitutions != null) {
            sbs = substitutions.toString();
        }
        return "Commentary{" +
                "liveticker=" + tkr +
                ", substitutions=" + sbs +
                '}';
    }
}
