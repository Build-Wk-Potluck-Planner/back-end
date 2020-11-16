package com.buildweek.potluckplanner.models;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventid;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String date;

    // attendees

    // foodlist

    public Event()
    {
    }

    public Event(
        String location,
        String date)
    {
        this.location = location;
        this.date = date;
    }

    public long getEventid()
    {
        return eventid;
    }

    public void setEventid(long eventid)
    {
        this.eventid = eventid;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
