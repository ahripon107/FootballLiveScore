package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamSquadPlayer implements Serializable{
    private String id;
    private String name;
    private String number;
    private String age;
    private String position;
    private String injured;
    private String minutes;
    private String appearences;
    private String lineups;
    private String substituteIn;
    private String substituteOut;
    private String substitutesOnBench;
    private String goals;
    private String yellowCards;
    private String yellowRed;
    private String redCards;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInjured() {
        return injured;
    }

    public void setInjured(String injured) {
        this.injured = injured;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getAppearences() {
        return appearences;
    }

    public void setAppearences(String appearences) {
        this.appearences = appearences;
    }

    public String getLineups() {
        return lineups;
    }

    public void setLineups(String lineups) {
        this.lineups = lineups;
    }

    @JsonProperty("substitute_in")
    public String getSubstituteIn() {
        return substituteIn;
    }

    public void setSubstituteIn(String substituteIn) {
        this.substituteIn = substituteIn;
    }

    @JsonProperty("substitute_out")
    public String getSubstituteOut() {
        return substituteOut;
    }

    public void setSubstituteOut(String substituteOut) {
        this.substituteOut = substituteOut;
    }

    @JsonProperty("substitutes_on_bench")
    public String getSubstitutesOnBench() {
        return substitutesOnBench;
    }

    public void setSubstitutesOnBench(String substitutesOnBench) {
        this.substitutesOnBench = substitutesOnBench;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    @JsonProperty("yellowcards")
    public String getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(String yellowCards) {
        this.yellowCards = yellowCards;
    }

    @JsonProperty("yellowred")
    public String getYellowRed() {
        return yellowRed;
    }

    public void setYellowRed(String yellowRed) {
        this.yellowRed = yellowRed;
    }

    @JsonProperty("redcards")
    public String getRedCards() {
        return redCards;
    }

    public void setRedCards(String redCards) {
        this.redCards = redCards;
    }

    @Override
    public String toString() {
        return "TeamSquadPlayer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", age='" + age + '\'' +
                ", position='" + position + '\'' +
                ", injured='" + injured + '\'' +
                ", minutes='" + minutes + '\'' +
                ", appearences='" + appearences + '\'' +
                ", lineups='" + lineups + '\'' +
                ", substituteIn='" + substituteIn + '\'' +
                ", substituteOut='" + substituteOut + '\'' +
                ", substitutesOnBench='" + substitutesOnBench + '\'' +
                ", goals='" + goals + '\'' +
                ", yellowCards='" + yellowCards + '\'' +
                ", yellowRed='" + yellowRed + '\'' +
                ", redCards='" + redCards + '\'' +
                '}';
    }
}
