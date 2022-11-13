package com.example.resumeserver.components;

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

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "LocationDto{" + "city='" + city + '\'' + ", country='" + country + '\'' + ", street='" + street + '\'' + '}';
    }
}
