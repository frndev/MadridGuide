package com.example.fran.madridguide.model;

import java.io.Serializable;

/**
 * Created by fran on 15/1/17.
 */

public class Activity implements Serializable {

    private long id;
    private String name;
    private String imageURL;
    private String logoImageURL;
    private String address;
    private String descriptionES;
    private String descriptionEN;
    private String url;
    private Double latitude;
    private Double longitude;

    public Activity(long id,String name) {
        this.name = name;
        this.id = id;
    }

    private Activity(){}

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public Activity setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Activity setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Activity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getLogoImageURL() {
        return logoImageURL;
    }

    public Activity setLogoImageURL(String logoImageURL) {
        this.logoImageURL = logoImageURL;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Activity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescriptionES() {
        return descriptionES;
    }

    public Activity setDescriptionES(String descriptionES) {
        this.descriptionES = descriptionES;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Activity setUrl(String url) {
        this.url = url;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Activity setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
}
