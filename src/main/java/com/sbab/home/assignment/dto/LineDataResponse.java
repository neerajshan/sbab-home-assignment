package com.sbab.home.assignment.dto;

import lombok.Data;

import java.util.List;


@Data
public class LineDataResponse {
    String StatusCode;
    List<BusJourResponse> busJourResponseList;

}
