package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;

import java.util.Collection;
import java.util.List;


public interface BusService {

    BusStopsResponse findAllStopsForBusnumber(String busnumber);


    Collection<String> getAllBuses();


    void refresh(List<Businformation> businformationList);


    List<TopBusLinesStopsResponse> getBusLinesWithMaxnumberOfBusStops(int limit);

}
