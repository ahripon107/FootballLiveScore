package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchLineup implements Serializable{
    private List<MatchLineupPlayer> localteam;
    private List<MatchLineupPlayer> visitorteam;

    public List<MatchLineupPlayer> getLocalteam() {
        return localteam;
    }

    public void setLocalteam(List<MatchLineupPlayer> localteam) {
        this.localteam = localteam;
    }

    public List<MatchLineupPlayer> getVisitorteam() {
        return visitorteam;
    }

    public void setVisitorteam(List<MatchLineupPlayer> visitorteam) {
        this.visitorteam = visitorteam;
    }

    @Override
    public String toString() {
        String local = "";
        String visitor = "";

        if (localteam != null) {
            for (int i=0;i<localteam.size();i++) {
                local += localteam.get(i);
            }
        }

        if (visitorteam != null) {
            for (int i=0;i<visitorteam.size();i++) {
                visitor += visitorteam.get(i);
            }
        }

        return "MatchLineup{" +
                "localteam=" + local +
                ", visitorteam=" + visitor +
                '}';
    }
}
