package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.Event;
import com.buildweek.potluckplanner.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "eventService")
public class EventServiceImpl implements EventService
{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll()
    {
        List<Event> list = new ArrayList<>();
        eventRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Event findEventById(long eventid)
    {
        return eventRepository.findById(eventid)
            .orElseThrow(() -> new EntityNotFoundException("Event id " + eventid + " not found!"));
    }

    @Override
    public Event save(Event event)
    {
        Event newEvent = new Event();

        if (event.getEventid() != 0)
        {
            eventRepository.findById(event.getEventid())
                .orElseThrow(() -> new EntityNotFoundException("Event id " + event.getEventid() + " not found!"));
            newEvent.setEventid(event.getEventid());
        }

//        newEvent.setName(event.getName());
        newEvent.setLocation(event.getLocation());
        newEvent.setDate(event.getDate());
        newEvent.setTime(event.getTime());
//        newEvent.setOrganizer(event.getOrganizer());

        return eventRepository.save(newEvent);
    }

    @Override
    public void delete(long eventid)
    {
        eventRepository.findById(eventid)
            .orElseThrow(() -> new EntityNotFoundException("Event id " + eventid + " not found!"));
        eventRepository.deleteById(eventid);
    }
}
