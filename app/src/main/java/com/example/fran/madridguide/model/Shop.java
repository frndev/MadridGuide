package com.example.fran.madridguide.model;

import java.io.Serializable;

/**
 * Created by fran on 8/1/17.
 */

public class Shop implements Serializable {

    public long id;
    private String name;
    private String imageURL;
    private String logoImageURL;
    private String address;
    private String url;
    private String description;
    private Double latitude;
    private Double longitude;

    public Shop(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Shop(){}

    public long getId() {
        return id;
    }

    public Shop setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Shop setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getLogoImageURL() {
        return logoImageURL;
    }

    public Shop setLogoImageURL(String logoImageURL) {
        this.logoImageURL = logoImageURL;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Shop setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Shop setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Shop setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Shop setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
