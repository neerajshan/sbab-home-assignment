package com.neeraj.test.demoproject.integrationTest.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String superSecretSecret;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getSuperSecretSecret() {
        return superSecretSecret;
    }


    public void setSuperSecretSecret(String superSecretSecret) {
        this.superSecretSecret = superSecretSecret;
    }


    @Override
    public String toString() {
        return "UserDto{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", twofakey='" + superSecretSecret + '\'' +
               '}';
    }
}

