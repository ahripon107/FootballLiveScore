package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveTicker implements Serializable {

    private String important;
    private String isgoal;
    private String minute;
    private String comment;

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getIsgoal() {
        return isgoal;
    }

    public void setIsgoal(String isgoal) {
        this.isgoal = isgoal;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "LiveTicker{" +
                "important='" + important + '\'' +
                ", isgoal='" + isgoal + '\'' +
                ", minute='" + minute + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
