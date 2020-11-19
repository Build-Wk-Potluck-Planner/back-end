package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.Event;

import java.util.List;

public interface EventService
{
    List<Event> findAll();

    Event findEventById(long eventid);

    Event save(Event event);

    void delete(long eventid);
}
