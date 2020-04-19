package com.neeraj.test.demoproject.integrationTest.dto;

import java.util.Collection;


public class BusStopsResponse {
    String busnumber;
    Collection<String> stops;


    public String getBusnumber() {
        return busnumber;
    }


    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }


    public Collection<String> getStops() {
        return stops;
    }


    public void setStops(Collection<String> stops) {
        this.stops = stops;
    }
}
