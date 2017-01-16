package com.example.fran.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fran on 15/1/17.
 */

public class ShopEntity {

    @SerializedName("id") private Long id;
    @SerializedName("name") private String name;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logoImg;
    @SerializedName("address") private String address;
    @SerializedName("url") private String url;
    @SerializedName("description_es") private String descriptionES;
    @SerializedName("description_en") private String descriptionEN;
    @SerializedName("gps_lat") private Double latitude;
    @SerializedName("gps_lon") private Double longitude;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getDescriptionES() {
        return descriptionES;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
