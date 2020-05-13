package com.geolo.api.models;

import java.util.ArrayList;

public class Provider {

    private String name;
    private ArrayList<String> specialtys;
    private LatLong latLong;

    public Provider(String name, String specialty, LatLong latLong) {
        this.name = name;
        this.specialtys = new ArrayList<>();
        specialtys.add(specialty);
        this.latLong = latLong;
    }

    public Provider(String name, ArrayList<String> specialtys, LatLong latLong) {
        this.name = name;
        this.specialtys = specialtys;
        this.latLong = latLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSpecialtys() {
        return specialtys;
    }

    public void setSpecialtys(ArrayList<String> specialtys) {
        this.specialtys = specialtys;
    }

    public LatLong getLatLong() {
        return latLong;
    }

    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

    public void addSpecialty(String specialty) {
        specialtys.add(specialty);
    }

}
