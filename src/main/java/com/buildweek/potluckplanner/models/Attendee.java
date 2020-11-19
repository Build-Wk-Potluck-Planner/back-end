package com.buildweek.potluckplanner.models;

import javax.persistence.*;
import java.io.Serializable;

// join table for Users and Events

@Entity
@Table(name = "attendees")
@IdClass(AttendeeId.class)
public class Attendee extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;

    public Attendee()
    {
    }

    public Attendee(
        User user,
        Event event)
    {
        this.user = user;
        this.event = event;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Attendee that = (Attendee) o;
        return (((this.user == null) ? 0 : this.user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid())) &&
               (((this.event == null) ? 0 : this.event.getEventid()) == ((that.event == null) ? 0 : that.event.getEventid()));
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
