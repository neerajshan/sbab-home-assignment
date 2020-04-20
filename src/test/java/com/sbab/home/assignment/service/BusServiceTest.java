package com.sbab.home.assignment.service;

import com.sbab.home.assignment.db.model.Businformation;
import com.sbab.home.assignment.db.repository.BusInformationRepository;
import com.sbab.home.assignment.db.repository.BusInformationRepository.TopBusResult;
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


@RunWith(SpringRunner.class)
public class BusServiceTest {

    @Autowired
    BusService busServiceImpl;

    @Autowired
    BusInformationRepository busInformationRepository;

    List<Businformation> businformationList = new ArrayList<>();

    List<TopBusResult> findTopBus = new ArrayList<>();


    @Before
    public void setUp() {

        setupInitData();

        Mockito.when(busInformationRepository.findByBusnumber("101"))
                .thenReturn(businformationList);

        Mockito.when(busInformationRepository.findTopBusNumbers())
                .thenReturn(findTopBus);
    }


    private void setupInitData() {
        for (int index = 10; index < 100; index++) {
            Businformation businformation = new Businformation();
            businformation.setBusnumber("101");
            businformation.setBusstopnumber(" " + index);

            int finalIndex = index;
            findTopBus.add(
                    new TopBusResult() {
                        @Override
                        public String getBusNumber() {
                            return "" + finalIndex;
                        }


                        @Override
                        public Integer getStopCounts() {
                            return finalIndex * 10;
                        }
                    });

            businformationList.add(businformation);
        }
    }


    @Test
    public void findAllStopsForBusenumberTest() {
        final BusStopsResponse allStopsForBusenumber = busServiceImpl.findAllStopsForBusnumber("101");
        final Collection<String> stops = allStopsForBusenumber.getStops();
        assertEquals(stops.size(), businformationList.size());
    }


    @Test
    public void getBusLinesWithMaxnumberOfBusStopsTest() {
        List<TopBusLinesStopsResponse> topBusLinesStopsResponseList = busServiceImpl.getBusLinesWithMaxnumberOfBusStops(10);
        assertEquals(topBusLinesStopsResponseList.size(), 10);

//        topBusLinesStopsResponseList.stream().forEach(
//                topBusLinesStopsResponse -> assertTrue(findTopBus.contains(topBusLinesStopsResponse))
//        );

        topBusLinesStopsResponseList = busServiceImpl.getBusLinesWithMaxnumberOfBusStops(7);
        assertEquals(topBusLinesStopsResponseList.size(), 7);

        topBusLinesStopsResponseList = busServiceImpl.getBusLinesWithMaxnumberOfBusStops(5);
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
            return new BusServiceImpl(busInformationRepository, modelMapper);
        }
    }
}