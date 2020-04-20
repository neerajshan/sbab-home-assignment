package com.sbab.home.assignment.dto;

import java.util.Objects;


public class TopBusLinesStopsResponse {
    String busNumer;
    String noOfStops;


    public TopBusLinesStopsResponse() {
    }


    public TopBusLinesStopsResponse(String busNumer, String noOfStops) {
        this.busNumer = busNumer;
        this.noOfStops = noOfStops;
    }


    public String getBusNumer() {
        return busNumer;
    }


    public void setBusNumer(String busNumer) {
        this.busNumer = busNumer;
    }


    public String getNoOfStops() {
        return noOfStops;
    }


    public void setNoOfStops(String noOfStops) {
        this.noOfStops = noOfStops;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopBusLinesStopsResponse)) {
            return false;
        }
        TopBusLinesStopsResponse that = (TopBusLinesStopsResponse) o;
        return Objects.equals(getBusNumer(), that.getBusNumer()) &&
               Objects.equals(getNoOfStops(), that.getNoOfStops());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getBusNumer(), getNoOfStops());
    }
}
