package com.cdac.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

/**
 *
 * @author Suhas
 */
@Entity(name = "geo_lite_city")
public class GeoLiteCity {

    @Id
    @Column
    private long locId;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String country;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String region;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String city;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String postalCode;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String latitude;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String longitude;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String metroCode;

    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String areaCode;

    public GeoLiteCity() {
    }

    public GeoLiteCity(long locId, String country, String region, String city, String postalCode, String latitude, String longitude, String metroCode, String areaCode) {
        this.locId = locId;
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metroCode = metroCode;
        this.areaCode = areaCode;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(String metroCode) {
        this.metroCode = metroCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
