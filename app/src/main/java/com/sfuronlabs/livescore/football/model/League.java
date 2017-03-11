package com.sfuronlabs.livescore.football.model;

import com.google.inject.Inject;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class League implements Serializable{
    private String league;
    private String key;
    private String hi;

    private List<MatchSummary> matches;

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

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public List<MatchSummary> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchSummary> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        String mtch = "";
        for (int i=0;i<matches.size();i++) {
            mtch += matches.get(i).toString();
        }
        return "League{" +
                "league='" + league + '\'' +
                ", key='" + key + '\'' +
                ", hi='" + hi + '\'' +
                ", matches=" + mtch +
                '}';
    }
}
