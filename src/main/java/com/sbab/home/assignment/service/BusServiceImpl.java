package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.db.repository.BusInformationRepository.TopBusResult;
import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;
import com.sbab.home.assignment.exceptionhandler.exceptions.BusNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BusServiceImpl implements BusService {

    private static final Logger LOG = LoggerFactory.getLogger(BusServiceImpl.class);
    BusInformationRepository busInformationRepository;


    @Autowired
    public BusServiceImpl(BusInformationRepository busInformationRepository) {
        this.busInformationRepository = busInformationRepository;
    }


    @Override
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
    @Override
    public Collection<String> getAllBuses() {
        return (busInformationRepository.findAllUniqueBusNumbers());
    }


    private TopBusLinesStopsResponse convertToDto(TopBusResult topBusResult) {
        return new TopBusLinesStopsResponse(topBusResult.getBusNumber(), topBusResult.getStopCounts().toString());
    }


    // intended to be called by schedulers
    @Override
    public void refresh(List<Businformation> businformationList) {
        busInformationRepository.deleteAll();
        busInformationRepository.saveAll(businformationList);
    }


    // method is generic so user can decide how much results they want
    @Override
    public List<TopBusLinesStopsResponse> getBusLinesWithMaxnumberOfBusStops(int maxResult) {
        final List<TopBusResult> topBusNumbers = busInformationRepository.findTopBusNumbers();
        return topBusNumbers.stream()
                .map(this::convertToDto)
                .limit(maxResult)
                .collect(Collectors.toList());
    }
}
