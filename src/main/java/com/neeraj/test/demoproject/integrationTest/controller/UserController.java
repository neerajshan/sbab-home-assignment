package com.neeraj.test.demoproject.integrationTest.controller;

import com.neeraj.test.demoproject.integrationTest.ExceptionHandler.UserNotFoundException;
import com.neeraj.test.demoproject.integrationTest.dao.UserRepository;
import com.neeraj.test.demoproject.integrationTest.db.Users;
import com.neeraj.test.demoproject.integrationTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    UserService userService;
    UserRepository userRepository;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> greeting() {
        return userService.getAllUsers();
    }


    @GetMapping("/filter")
    public Page<Users> filterBooks(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Users getUser(@PathVariable("id") long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with given Id " + id + "not found "));
    }
}
