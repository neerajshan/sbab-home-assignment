package com.sbab.home.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIError {
    String message;
    String trackingCode;


    public APIError() {
    }


    public APIError(String message) {
        this.message = message;
    }


    public APIError(String message, String trackingCode) {
        this.message = message;
        this.trackingCode = trackingCode;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getTrackingCode() {
        return trackingCode;
    }


    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }


    @Override
    public String toString() {
        return "APIError{" +
               "message='" + message + '\'' +
               ", trackingCode='" + trackingCode + '\'' +
               '}';
    }
}
