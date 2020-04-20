package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;
import com.sbab.home.assignment.exceptionhandler.exceptions.BusNotFoundException;
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

    private static final Logger LOG = LoggerFactory.getLogger(BusService.class);
    BusInformationRepository busInformationRepository;
    ModelMapper modelMapper;


    @Autowired
    public BusService(BusInformationRepository busInformationRepository, ModelMapper modelMapper) {
        this.busInformationRepository = busInformationRepository;
        this.modelMapper = modelMapper;
    }


    public BusStopsResponse findAllStopsForBusnumber(String busnumber) {
        BusStopsResponse busStopsResponse = new BusStopsResponse();
        Collection<String> stops = new ArrayList<>();
        busStopsResponse.setBusnumber(busnumber);
        final Collection<Businformation> byBusnumber = busInformationRepository.findByBusnumber(busnumber);
        if (byBusnumber.size() == 0) // Invalid bus number
        {
            LOG.warn("Input Bus no {} is not a valid bus number", busnumber);
            throw new BusNotFoundException(String.format("Bus no %s is not a valid busnumber", busnumber));
        }

        for (Businformation bs : byBusnumber) {
            stops.add(bs.getBusstopnumber());
        }
        busStopsResponse.setStops(stops);
        return busStopsResponse;
    }


    // return list of unique bus numbers
    public Collection<String> getAllBuses() {
        return (busInformationRepository.findAllUniqueBusNumbers());
    }


    private BusStopsResponse convertToDto(Businformation businformation) {
        return modelMapper.map(businformation, BusStopsResponse.class);
    }


    // intended to be called by schedulers
    public void refresh(List<Businformation> businformationList) {
        busInformationRepository.deleteAll();
        busInformationRepository.saveAll(businformationList);
    }


    // method is generic so user can decide how much results they want
    public List<TopBusLinesStopsResponse> getBusLinesWithMaxnumberOfBusStops(int max) {
        List<TopBusLinesStopsResponse> busNumber = new ArrayList<>();
        final Object[][] topBusNumbers = busInformationRepository.findTopBusNumbers();

        for (int index = 0; (index < topBusNumbers.length) && (index < max); index++) {
            TopBusLinesStopsResponse topBusLinesStopsResponse = new TopBusLinesStopsResponse();
            Object[] topBusNumberRow = topBusNumbers[index];
            topBusLinesStopsResponse.setBusNumer(topBusNumberRow[0].toString());
            topBusLinesStopsResponse.setNoOfStops(topBusNumberRow[1].toString());
            busNumber.add(topBusLinesStopsResponse);
        }
        return busNumber;
    }
}
