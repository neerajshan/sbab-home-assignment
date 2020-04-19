package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class BusService {

    BusInformationRepository busInformationRepository;
    ModelMapper modelMapper;

    private static final Logger LOG = LoggerFactory.getLogger(BusService.class);
    @Autowired
    public BusService(BusInformationRepository busInformationRepository, ModelMapper modelMapper) {
        this.busInformationRepository = busInformationRepository;
        this.modelMapper = modelMapper;
    }


    public BusStopsResponse findAllStopsForBusenumber(String busnumber) {
        BusStopsResponse busStopsResponse = new BusStopsResponse();
        Collection<String> stops = new ArrayList<>();
        busStopsResponse.setBusnumber(busnumber);
        for (Businformation bs : busInformationRepository.findByBusnumber(busnumber)) {
            stops.add(bs.getBusstopnumber());
        }
        busStopsResponse.setStops(stops);
        return busStopsResponse;
    }


    public Collection<String> getAllBuses() {
        return (busInformationRepository.findAllUniqueBusNumbers());
    }


    private BusStopsResponse convertToDto(Businformation businformation) {
        return modelMapper.map(businformation, BusStopsResponse.class);
    }


    public void saveAllBusinformation(List<Businformation> businformationList) {
        busInformationRepository.saveAll(businformationList);
    }


    public List<TopBusLinesStopsResponse> getBusLinesWithMaxnumberOfBusStops(int max) {
        List<TopBusLinesStopsResponse> busNumber = new ArrayList<>();
        final Object[][] topBusNumbers = busInformationRepository.findTopBusNumbers();
        for (int i = 0; (i < topBusNumbers.length) && (i < max); i++) {
            TopBusLinesStopsResponse topBusLinesStopsResponse = new TopBusLinesStopsResponse();
            Object[] topBusNumberRow = topBusNumbers[i];
            topBusLinesStopsResponse.setBusNumer(topBusNumberRow[0].toString());
            topBusLinesStopsResponse.setNoOfStops(topBusNumberRow[1].toString());
            busNumber.add(topBusLinesStopsResponse);
        }
        return busNumber;
    }
}
