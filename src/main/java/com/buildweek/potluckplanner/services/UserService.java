package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User findUserById(long userid);

    User save(User user);

    void delete(long userid);

    void deleteAll();
}
