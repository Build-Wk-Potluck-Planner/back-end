package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.Role;
import com.buildweek.potluckplanner.models.User;
import com.buildweek.potluckplanner.models.UserRoles;
import com.buildweek.potluckplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long userid)
    {
        return userRepository.findById(userid)
            .orElseThrow(() -> new EntityNotFoundException("User id " + userid + " not found!"));
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userRepository.findById(user.getUserid())
                .orElseThrow(() -> new EntityNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setFullname(user.getFullname());
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser, addRole));
        }

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void delete(long userid)
    {
        userRepository.findById(userid)
            .orElseThrow(() -> new EntityNotFoundException("User id " + userid + " not found!"));
        userRepository.deleteById(userid);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userRepository.deleteAll();
    }
}
