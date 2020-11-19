package com.buildweek.potluckplanner.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AttendeeId implements Serializable
{
    private long user;
    private long event;

    public AttendeeId()
    {
    }

    public AttendeeId(
        long user,
        long event)
    {
        this.user = user;
        this.event = event;
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getEvent()
    {
        return event;
    }

    public void setEvent(long event)
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
        AttendeeId that = (AttendeeId) o;
        return this.user == that.user &&
            this.event == that.event;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
