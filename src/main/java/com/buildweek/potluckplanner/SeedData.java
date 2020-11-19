package com.buildweek.potluckplanner;

import com.buildweek.potluckplanner.models.Role;
import com.buildweek.potluckplanner.models.User;
import com.buildweek.potluckplanner.models.UserRoles;
import com.buildweek.potluckplanner.services.RoleService;
import com.buildweek.potluckplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//@Component
public class SeedData
        implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        // admin, user
        User u1 = new User("admin",
                           "admin",
                           "$2y$12$.3EShnTthsvkGHLgzGXuvOdBmrhxLXU5EwiV6OeNwZcdrS.VFncu2");
        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1.getRoles()
                .add(new UserRoles(u1, r2));

        userService.save(u1);
    }
}