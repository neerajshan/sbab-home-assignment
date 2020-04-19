package com.neeraj.test.demoproject.integrationTest.service;

import com.neeraj.test.demoproject.integrationTest.dao.UserRepository;
import com.neeraj.test.demoproject.integrationTest.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Users getUserByname(String name) {
        return userRepository.findByUsername(name);
    }


    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
