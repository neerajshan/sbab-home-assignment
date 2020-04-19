package com.neeraj.test.demoproject.integrationTest.service;

import com.neeraj.test.demoproject.integrationTest.db.model.Businformation;
import com.neeraj.test.demoproject.integrationTest.db.repository.BusInformationRepository;
import com.neeraj.test.demoproject.integrationTest.dto.BusStopsResponse;
import com.neeraj.test.demoproject.integrationTest.dto.TopBusLinesStopsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class BusService {

    BusInformationRepository busInformationRepository;
    ModelMapper modelMapper;


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


    public List<TopBusLinesStopsResponse> getBusLinesWithMaxnumberOfBusStops(int max) {
        List<TopBusLinesStopsResponse> busNumber = new ArrayList<>();
        final Object[] topBusNumbers = busInformationRepository.findTopBusNumbers();
        for (int i = 0; i < topBusNumbers.length & i < (max - 1); i++) {
            TopBusLinesStopsResponse topBusLinesStopsResponse = new TopBusLinesStopsResponse();
            Object[] topBusNumberRow = (Object[]) topBusNumbers[i];
            topBusLinesStopsResponse.setBusNumer(topBusNumberRow[0].toString());
            topBusLinesStopsResponse.setNoOfStops(topBusNumberRow[1].toString());
            busNumber.add(topBusLinesStopsResponse);
        }
        return busNumber;
    }
}
