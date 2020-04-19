package com.sbab.home.assignment.dto;

import java.util.Objects;


public class BusJourResponse {

    private String linenumber;
    private String directioncode;
    private String journeypatternpointnumber;


    public BusJourResponse() {
    }


    public String getLinenumber() {
        return linenumber;
    }


    public void setLinenumber(String linenumber) {
        this.linenumber = linenumber;
    }


    public String getDirectioncode() {
        return directioncode;
    }


    public void setDirectioncode(String directioncode) {
        this.directioncode = directioncode;
    }


    public String getJourneypatternpointnumber() {
        return journeypatternpointnumber;
    }


    public void setJourneypatternpointnumber(String journeypatternpointnumber) {
        this.journeypatternpointnumber = journeypatternpointnumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusJourResponse)) {
            return false;
        }
        BusJourResponse that = (BusJourResponse) o;
        return Objects.equals(getLinenumber(), that.getLinenumber()) &&
               Objects.equals(getDirectioncode(), that.getDirectioncode()) &&
               Objects.equals(getJourneypatternpointnumber(), that.getJourneypatternpointnumber());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getLinenumber(), getDirectioncode(), getJourneypatternpointnumber());
    }
}
