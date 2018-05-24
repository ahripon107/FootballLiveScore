package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements Serializable{
    private String id;
    private String lastUpdated;
    private String teamName;
    private String country;
    private String venue;
    private String venueId;
    private String venueSurface;
    private String venueCapacity;
    private String venueAddress;
    private String founded;
    private String coach;
    private String coachId;

    private List<TeamSquadPlayer> squad;

    private Transfers transfers;
    private String venueCity;

    private List<TeamSidelinedPlayer> sidelined;
    private List<MatchSummary> fixtures;
    private List<TeamLeague> leagues;

    private String shape;
    private List<String> shapeFixtures;

    @JsonProperty("id_gs")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("teamname")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @JsonProperty("venueid")
    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @JsonProperty("venuesurface")
    public String getVenueSurface() {
        return venueSurface;
    }

    public void setVenueSurface(String venueSurface) {
        this.venueSurface = venueSurface;
    }

    @JsonProperty("venuecapacity")
    public String getVenueCapacity() {
        return venueCapacity;
    }

    public void setVenueCapacity(String venueCapacity) {
        this.venueCapacity = venueCapacity;
    }

    @JsonProperty("venueaddress")
    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @JsonProperty("coachid")
    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public List<TeamSquadPlayer> getSquad() {
        return squad;
    }

    public void setSquad(List<TeamSquadPlayer> squad) {
        this.squad = squad;
    }

    public Transfers getTransfers() {
        return transfers;
    }

    public void setTransfers(Transfers transfers) {
        this.transfers = transfers;
    }

    @JsonProperty("venuecity")
    public String getVenueCity() {
        return venueCity;
    }

    public void setVenueCity(String venueCity) {
        this.venueCity = venueCity;
    }

    public List<TeamSidelinedPlayer> getSidelined() {
        return sidelined;
    }

    public void setSidelined(List<TeamSidelinedPlayer> sidelined) {
        this.sidelined = sidelined;
    }

    public List<MatchSummary> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<MatchSummary> fixtures) {
        this.fixtures = fixtures;
    }

    public List<TeamLeague> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<TeamLeague> leagues) {
        this.leagues = leagues;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @JsonProperty("shape_fixtures")
    public List<String> getShapeFixtures() {
        return shapeFixtures;
    }

    public void setShapeFixtures(List<String> shapeFixtures) {
        this.shapeFixtures = shapeFixtures;
    }

    @Override
    public String toString() {
        StringBuilder squads = new StringBuilder();
        StringBuilder sideline = new StringBuilder();
        StringBuilder fixture = new StringBuilder();
        StringBuilder league = new StringBuilder();
        StringBuilder shapeFixture = new StringBuilder();

        for (int i=0;i<squad.size();i++) {
            squads.append(squad.get(i).toString());
        }

        for (int i=0;i<sidelined.size();i++) {
            sideline.append(sidelined.get(i).toString());
        }

        for (int i=0;i<fixtures.size();i++) {
            fixture.append(fixtures.get(i).toString());
        }

        for (int i=0;i<leagues.size();i++) {
            league.append(leagues.get(i).toString());
        }

        for (int i=0;i<shapeFixtures.size();i++) {
            shapeFixture.append(shapeFixtures.get(i));
        }


        return "Team{" +
                "id='" + id + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", teamName='" + teamName + '\'' +
                ", country='" + country + '\'' +
                ", venue='" + venue + '\'' +
                ", venueId='" + venueId + '\'' +
                ", venueSurface='" + venueSurface + '\'' +
                ", venueCapacity='" + venueCapacity + '\'' +
                ", venueAddress='" + venueAddress + '\'' +
                ", founded='" + founded + '\'' +
                ", coach='" + coach + '\'' +
                ", coachId='" + coachId + '\'' +
                ", squad=" + squads +
                ", transfers=" + transfers.toString() +
                ", venueCity='" + venueCity + '\'' +
                ", sidelined=" + sideline +
                ", fixtures=" + fixture +
                ", leagues=" + league +
                ", shape='" + shape + '\'' +
                ", shapeFixtures=" + shapeFixture +
                '}';
    }
}
