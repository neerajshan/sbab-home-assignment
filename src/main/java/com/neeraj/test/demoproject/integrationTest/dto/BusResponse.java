package com.neeraj.test.demoproject.integrationTest.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class BusResponse {
    Set<String> busNumbers = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusResponse)) {
            return false;
        }
        BusResponse that = (BusResponse) o;
        return Objects.equals(getBusNumbers(), that.getBusNumbers());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getBusNumbers());
    }


    public Set<String> getBusNumbers() {
        return busNumbers;
    }


    public void setBusNumbers(Set<String> busNumbers) {
        this.busNumbers = busNumbers;
    }
}
