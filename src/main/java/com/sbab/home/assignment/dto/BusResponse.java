package com.sbab.home.assignment.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class BusResponse {
    Set<String> busNumbers = new HashSet<>();

}
