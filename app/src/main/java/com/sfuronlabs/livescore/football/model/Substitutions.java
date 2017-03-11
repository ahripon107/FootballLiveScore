package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Substitutions implements Serializable {
    private List<MatchSubstitutionPlayer> localteam;
    private List<MatchSubstitutionPlayer> visitorteam;

    public List<MatchSubstitutionPlayer> getLocalteam() {
        return localteam;
    }

    public void setLocalteam(List<MatchSubstitutionPlayer> localteam) {
        this.localteam = localteam;
    }

    public List<MatchSubstitutionPlayer> getVisitorteam() {
        return visitorteam;
    }

    public void setVisitorteam(List<MatchSubstitutionPlayer> visitorteam) {
        this.visitorteam = visitorteam;
    }

    @Override
    public String toString() {
        String local = "";
        String visit = "";
        if (localteam != null && visitorteam != null) {
            for (int i=0;i<localteam.size();i++) {
                local += localteam.get(i).toString();
            }

            for (int i=0;i<visitorteam.size();i++) {
                visit += visitorteam.get(i).toString();
            }
        }
        return "Substitutions{" +
                "localteam=" + local +
                ", visitorteam=" + visit +
                '}';
    }
}
