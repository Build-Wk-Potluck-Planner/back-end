package com.buildweek.potluckplanner.repository;

import com.buildweek.potluckplanner.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>
{
}
