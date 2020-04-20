package com.sbab.home.assignment.dto;

import lombok.Data;

import java.util.Collection;


@Data
public class BusStopsResponse {
    String busnumber;
    Collection<String> stops;

}
