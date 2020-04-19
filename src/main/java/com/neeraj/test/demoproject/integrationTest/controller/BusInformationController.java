package com.neeraj.test.demoproject.integrationTest.controller;

import com.neeraj.test.demoproject.integrationTest.dto.BusStopsResponse;
import com.neeraj.test.demoproject.integrationTest.dto.TopBusLinesStopsResponse;
import com.neeraj.test.demoproject.integrationTest.service.BusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    ModelMapper modelMapper;


    @Autowired
    public BusInformationController(BusService busService, ModelMapper modelMapper) {
        this.busService = busService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<String> getAllBuses() {
        return busService.getAllBuses();
    }


    @GetMapping("/{busnumber}")
    @ResponseStatus(HttpStatus.OK)
    public BusStopsResponse findBusStopsForGivenBusnumber(@PathVariable("busnumber") String busnumber) {
        return busService.findAllStopsForBusenumber(busnumber);
    }


    @GetMapping("/topbuslines")
    @ResponseStatus(HttpStatus.OK)
    public List<TopBusLinesStopsResponse> getTopBusLines() {
        return busService.getBusLinesWithMaxnumberOfBusStops(10);
    }


}
