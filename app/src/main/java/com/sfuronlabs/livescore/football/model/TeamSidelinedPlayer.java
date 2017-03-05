package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamSidelinedPlayer {
    private String id;
    private String name;
    private String description;
    private String startdate;
    private String enddate;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "TeamSidelinedPlayer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }
}
