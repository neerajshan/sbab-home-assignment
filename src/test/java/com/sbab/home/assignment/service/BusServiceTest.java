package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.dto.BusStopsResponse;
import com.sbab.home.assignment.dto.TopBusLinesStopsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
public class BusServiceTest {

    @Autowired
    BusService busService;

    @Autowired
    BusInformationRepository busInformationRepository;

    List<Businformation> businformationList = new ArrayList<>();

    Object[][] findTopBus;


    @Before
    public void setUp() {

        findTopBus = new Object[10][2];

        for (int i = 10, j = 0; i > 0 && j < 10; j++, i--) {
            findTopBus[j] = new Object[2];
            findTopBus[j][0] = i;
            findTopBus[j][1] = i * 10;
        }

        for (int i = 1000; i < 2000; i++) {
            Businformation businformation = new Businformation();
            businformation.setBusnumber("101");
            businformation.setBusstopnumber(" " + i);
            businformationList.add(businformation);
        }

        Mockito.when(busInformationRepository.findByBusnumber("101"))
                .thenReturn(businformationList);

        Mockito.when(busInformationRepository.findTopBusNumbers())
                .thenReturn(findTopBus);
    }


    @Test
    public void findAllStopsForBusenumberTest() {
        final BusStopsResponse allStopsForBusenumber = busService.findAllStopsForBusnumber("101");
        final Collection<String> stops = allStopsForBusenumber.getStops();
        assertEquals(stops.size(), businformationList.size());
    }


    @Test
    public void getBusLinesWithMaxnumberOfBusStopsTest() {
        List<TopBusLinesStopsResponse> topBusLinesStopsResponseList = busService.getBusLinesWithMaxnumberOfBusStops(10);
        assertEquals(topBusLinesStopsResponseList.size(), 10);

        for (int i = 0; i < findTopBus.length; i++) {
            String busNumer = String.valueOf(findTopBus[i][0]);
            String noOfStops = String.valueOf(findTopBus[i][1]);
            TopBusLinesStopsResponse topBusLinesStopsResponse = new TopBusLinesStopsResponse(busNumer, noOfStops);
            assertTrue(topBusLinesStopsResponseList.contains(topBusLinesStopsResponse));
        }


        topBusLinesStopsResponseList = busService.getBusLinesWithMaxnumberOfBusStops(7);
        assertEquals(topBusLinesStopsResponseList.size(), 7);


        topBusLinesStopsResponseList = busService.getBusLinesWithMaxnumberOfBusStops(5);
        assertEquals(topBusLinesStopsResponseList.size(), 5);
    }


    @TestConfiguration
    static class BusServiceTestContextConfiguration {
        @MockBean
        BusInformationRepository busInformationRepository;

        @MockBean
        ModelMapper modelMapper;


        @Bean
        public BusService busService() {
            return new BusService(busInformationRepository, modelMapper);
        }
    }
}