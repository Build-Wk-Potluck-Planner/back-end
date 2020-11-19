package com.buildweek.potluckplanner.repository;

import com.buildweek.potluckplanner.models.Attendee;
import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>
{
}
