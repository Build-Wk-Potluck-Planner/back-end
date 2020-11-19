package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.exceptions.ResourceFoundException;
import com.buildweek.potluckplanner.exceptions.ResourceNotFoundException;
import com.buildweek.potluckplanner.models.Role;
import com.buildweek.potluckplanner.repository.RoleRepository;
import com.buildweek.potluckplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the RoleService Interface
 */
@Transactional
@Service(value = "roleService")
public class RoleServiceImpl
        implements RoleService
{
    /**
     * Connects this service to the Role Model
     */
    @Autowired
    RoleRepository rolerepos;

    /**
     * Connect this service to the User Model
     */
    @Autowired
    UserRepository userrepos;

    /**
     * Connects this service to the auditing service in order to get current user name
     */
    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        rolerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name)
    {
        Role rr = rolerepos.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new ResourceNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers()
                .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through Role.");
        }

        return rolerepos.save(role);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        rolerepos.deleteAll();
    }

    @Transactional
    @Override
    public Role update(long id,
                       Role role)
    {
        if (role.getName() == null)
        {
            throw new ResourceNotFoundException("No role name found to update!");
        }

        if (role.getUsers()
                .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        Role newRole = findRoleById(id); // see if id exists

        rolerepos.updateRoleName(userAuditing.getCurrentAuditor()
                                         .get(),
                                 id,
                                 role.getName());
        return findRoleById(id);
    }
}
