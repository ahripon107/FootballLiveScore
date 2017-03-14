package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedAppStart implements Serializable{

    private List<CountryAppStart> countries;

    public List<CountryAppStart> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryAppStart> countries) {
        this.countries = countries;
    }
}
