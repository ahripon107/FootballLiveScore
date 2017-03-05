package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private String id;
    private String name;
    private String lastUpdated;
    private String firstName;
    private String lastName;
    private String team;
    private String teamId;
    private String nationality;
    private String birthdate;
    private String age;
    private String birthCountry;
    private String birthPlace;
    private String position;
    private String weight;
    private String height;

    private List<Statistic> statistics;
    private List<Statistic> statisticsCups;
    private List<Statistic> statisticsCupsIntl;
    private List<Statistic> statisticsIntl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @JsonProperty("teamid")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JsonProperty("birthcountry")
    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    @JsonProperty("birthplace")
    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    @JsonProperty("statisticscups")
    public List<Statistic> getStatisticsCups() {
        return statisticsCups;
    }

    public void setStatisticsCups(List<Statistic> statisticsCups) {
        this.statisticsCups = statisticsCups;
    }

    @JsonProperty("statisticscupsintl")
    public List<Statistic> getStatisticsCupsIntl() {
        return statisticsCupsIntl;
    }

    public void setStatisticsCupsIntl(List<Statistic> statisticsCupsIntl) {
        this.statisticsCupsIntl = statisticsCupsIntl;
    }

    @JsonProperty("statisticsintl")
    public List<Statistic> getStatisticsIntl() {
        return statisticsIntl;
    }

    public void setStatisticsIntl(List<Statistic> statisticsIntl) {
        this.statisticsIntl = statisticsIntl;
    }

    @Override
    public String toString() {
        String statistic = "";
        String statisticsCup = "";
        String statisticsCupsInt = "";
        String statisticsInt = "";

        for (int i=0;i<statistics.size();i++) {
            statistic += statistics.get(i).toString();
        }

        for (int i=0;i<statisticsCups.size();i++) {
            statisticsCup += statisticsCups.get(i).toString();
        }

        for (int i=0;i<statisticsCupsIntl.size();i++) {
            statisticsCupsInt += statisticsCupsIntl.get(i).toString();
        }

        for (int i=0;i<statisticsIntl.size();i++) {
            statisticsInt += statisticsIntl.get(i).toString();
        }


        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", team='" + team + '\'' +
                ", teamId='" + teamId + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", age='" + age + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", position='" + position + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", statistics=" + statistic +
                ", statisticsCups=" + statisticsCup +
                ", statisticsCupsIntl=" + statisticsCupsInt +
                ", statisticsIntl=" + statisticsInt +
                '}';
    }
}
