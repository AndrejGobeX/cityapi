package com.cityapi.cityapi.models;

public class City {
    private int id;
    private String name;
    private String country;
    private String state_region;
    private int population;
    public enum Column{ZERO, ID, NAME, COUNTRY, STATE_REGION, POPULATION};

    public City(int id, String name, String country, String state_region, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.state_region = state_region;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState_region() {
        return state_region;
    }

    public void setState_region(String state_region) {
        this.state_region = state_region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
