package com.buildweek.potluckplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventid;

//    @Column(nullable = false)
//    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

//    @ManyToOne
//    @JoinColumn(name = "userid",
//        nullable = false)
//    private User organizer;

    public Event()
    {
    }

    public Event(
//        String name,
        String location,
        String date,
        String time)
    {
//        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public long getEventid()
    {
        return eventid;
    }

    public void setEventid(long eventid)
    {
        this.eventid = eventid;
    }

//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }

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

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

//    public User getOrganizer()
//    {
//        return organizer;
//    }
//
//    public void setOrganizer(User organizer)
//    {
//        this.organizer = organizer;
//    }
}
