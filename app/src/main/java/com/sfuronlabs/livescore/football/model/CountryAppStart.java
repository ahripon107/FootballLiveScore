package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryAppStart implements Serializable{
    private List<LeagueAppStart> leagues;
    private String country;

    public List<LeagueAppStart> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<LeagueAppStart> leagues) {
        this.leagues = leagues;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
