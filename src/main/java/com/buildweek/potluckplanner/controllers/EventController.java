package com.buildweek.potluckplanner.controllers;

import com.buildweek.potluckplanner.models.Event;
import com.buildweek.potluckplanner.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController
{
    @Autowired
    private EventService eventService;

    // http://localhost:2019/events/events
    @GetMapping(value = "/events",
        produces = "application/json")
    public ResponseEntity<?> listAllEvents()
    {
        List<Event> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // http://localhost:2019/events/event/1
    @GetMapping(value = "/event/{eventid}",
        produces = "application/json")
    public ResponseEntity<?> listEventById(@PathVariable long eventid)
    {
        Event e = eventService.findEventById(eventid);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    // http://localhost:2019/events/event
    @PostMapping(value = "/event",
        consumes = "application/json")
    public ResponseEntity<?> addNewEvent(@Valid @RequestBody Event event)
    {
        event.setEventid(0);
        event = eventService.save(event);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{eventid}")
            .buildAndExpand(event.getEventid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/events/event/1
    @PutMapping(value = "/event/{eventid}",
        consumes = "application/json")
    public ResponseEntity<?> updateEvent(@Valid @RequestBody Event updateEvent,
                                         @PathVariable long eventid)
    {
        updateEvent.setEventid(eventid);
        eventService.save(updateEvent);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/events/event/1
    @DeleteMapping(value = "/event/{eventid}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventid)
    {
        eventService.delete(eventid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
