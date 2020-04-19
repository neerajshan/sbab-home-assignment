package com.sbab.home.assignment.dto;

import java.util.List;


public class LineDataResponse {
    String StatusCode;
    List<BusJourResponse> busJourResponseList;


    public String getStatusCode() {
        return StatusCode;
    }


    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }


    public List<BusJourResponse> getBusJourResponseList() {
        return busJourResponseList;
    }


    public void setBusJourResponseList(List<BusJourResponse> busJourResponseList) {
        this.busJourResponseList = busJourResponseList;
    }
}
