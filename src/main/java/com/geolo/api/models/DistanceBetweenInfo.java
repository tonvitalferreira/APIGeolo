package com.geolo.api.models;

public class DistanceBetweenInfo {

    private String originAddress, myAddress;
    private Provider destinationProvider;
    private Distance distance;
    private Duration duration;

    public DistanceBetweenInfo(String originAddress, String myAddress, Provider destinationProvider, Distance distance, Duration duration) {
        this.originAddress = originAddress;
        this.myAddress = myAddress;
        this.destinationProvider = destinationProvider;
        this.distance = distance;
        this.duration = duration;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public Provider getDestinationProvider() {
        return destinationProvider;
    }

    public void setDestinationProvider(Provider destinationProvider) {
        this.destinationProvider = destinationProvider;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

}
