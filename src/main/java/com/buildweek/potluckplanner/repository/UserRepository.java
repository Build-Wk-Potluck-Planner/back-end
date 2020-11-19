package com.buildweek.potluckplanner.repository;

import com.buildweek.potluckplanner.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String toLowerCase);
}
