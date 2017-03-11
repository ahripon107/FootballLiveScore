package com.sfuronlabs.livescore.football.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ripon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchCommentry implements Serializable{
    private List<MatchEvent> events;
    private MatchStat stats;

    public List<MatchEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MatchEvent> events) {
        this.events = events;
    }

    public MatchStat getStats() {
        return stats;
    }

    public void setStats(MatchStat stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String event = "";
        String stat = "";
        for (int i=0;i<events.size();i++) {
            event += events.get(i);
        }
        if (stats != null) stat = stats.toString();
        return "MatchCommentry{" +
                "events=" + event +
                ", stats=" + stat +
                '}';
    }
}
