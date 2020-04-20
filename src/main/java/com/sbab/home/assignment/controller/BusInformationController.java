package com.sbab.home.assignment.controller;

import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;
import com.sbab.home.assignment.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/v1/bus")
public class BusInformationController {

    BusService busService;


    @Autowired
    public BusInformationController(BusService busService) {
        this.busService = busService;
    }


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<String> getAllBuses() {
        return busService.getAllBuses();
    }


    @GetMapping(value = "/{busnumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BusStopsResponse findBusStopsForGivenBusnumber(@PathVariable("busnumber") String busnumber) {
        return busService.findAllStopsForBusnumber(busnumber);
    }


    @GetMapping(value = "/topbuslines", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TopBusLinesStopsResponse> getTopBusLines() {
        return busService.getBusLinesWithMaxnumberOfBusStops(10);
    }


}
