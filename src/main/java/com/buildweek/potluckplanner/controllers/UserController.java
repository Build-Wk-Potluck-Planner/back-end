package com.buildweek.potluckplanner.controllers;

import com.buildweek.potluckplanner.models.User;
import com.buildweek.potluckplanner.services.UserService;
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
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    // http://localhost:2019/users/users
    @GetMapping(value = "/users",
        produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:2019/users/user/1
    @GetMapping(value = "/user/{userid}",
        produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable long userid)
    {
        User u = userService.findUserById(userid);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // http://localhost:2019/users/user
    @PostMapping(value = "/user",
        consumes = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User user)
    {
        user.setUserid(0);
        user = userService.save(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(user.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/users/user/1
    @PutMapping(value = "/user/{userid}",
        consumes = "application/json")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User updateUser,
                                        @PathVariable long userid)
    {
        updateUser.setUserid(userid);
        userService.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/users/user/1
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
