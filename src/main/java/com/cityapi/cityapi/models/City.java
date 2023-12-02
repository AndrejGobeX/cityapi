package com.cityapi.cityapi.models;

public class City {
    private int id;
    private String name;
    private String country;
    private String region;
    private int population;
    private double temp_c;
    public enum Column{ZERO, ID, NAME, COUNTRY, REGION, POPULATION};

    public City(int id, String name, String country, String region, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }
}
