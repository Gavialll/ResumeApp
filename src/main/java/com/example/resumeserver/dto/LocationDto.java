package com.example.resumeserver.dto;

public class LocationDto {
    private String city;
    private String country;
    private String street;

    public LocationDto(String city, String country, String street) {
        this.city = city;
        this.country = country;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "LocationDto{" + "city='" + city + '\'' + ", country='" + country + '\'' + ", street='" + street + '\'' + '}';
    }
}
