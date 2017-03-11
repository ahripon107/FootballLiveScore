package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchLineupPlayer implements Serializable{
    private String name;
    private String id;
    private String booking;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "MatchLineupPlayer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", booking='" + booking + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
