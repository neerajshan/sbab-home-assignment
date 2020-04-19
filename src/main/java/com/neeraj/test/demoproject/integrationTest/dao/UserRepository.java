package com.neeraj.test.demoproject.integrationTest.dao;

import com.neeraj.test.demoproject.integrationTest.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}