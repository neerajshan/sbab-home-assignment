package com.neeraj.test.demoproject.integrationTest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingController {

    @RequestMapping(method = {RequestMethod.GET}, value = {"/ping"})
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        return "ok";
    }


    @RequestMapping(method = {RequestMethod.GET}, value = {"/version"})
    @ResponseStatus(HttpStatus.OK)
    public String getVersion() {
        return "1.0";
    }

}
