package com.naman.redis.practice.Redis.Practice.controllers;

import com.naman.redis.practice.Redis.Practice.daos.UserDAO;
import com.naman.redis.practice.Redis.Practice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/naman-redis/users")
public class UserController {

    @Autowired
    private UserDAO userDao;


    // create user
    @PostMapping
    public User createUser(@RequestBody User user
    ) {

        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);


    }

    //get single user

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userDao.get(userId);
    }

    //find all
    @GetMapping
    public List<User> getAll() {

        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
        return collect;

    }

    //delete  user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userDao.delete(userId);
    }

}