/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author Suhas
 */
@Entity(name = "geo_name_table")
@Indexed
@AnalyzerDef(
        name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class)
            ,@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
        @Parameter(name = "language", value = "English")
    })})
public class GeoNames implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "country_code", length = 2)
    @Field
    private String countryCode;

    @Column(name = "postal_code", length = 20)
    @Field
    private String postalCode;

    @Column(name = "place_name", length = 180)
    @Field
    @Analyzer(definition = "customanalyzer")
    private String placeName;

    @Column(name = "admin_name1", length = 100)
    @Field
    private String adminName1;

    @Column(name = "admin_code1", length = 20)
    @Field
    private String adminCode1;

    @Column(name = "admin_name2", length = 100)
    @Field
    private String adminName2;

    @Column(name = "admin_code2", length = 20)
    @Field
    private String adminCode2;

    @Field
    @Column(name = "admin_name3", length = 100)
    private String adminName3;

    @Field
    @Column(name = "admin_code3", length = 20)
    private String adminCode3;

    @Field
    @Column(name = "latitude", length = 255)
    private String latitude;

    @Field
    @Column(name = "longitude", length = 255)
    private String longitude;

    @Column(name = "accuracy", length = 255)
    private String accuracy;

    public GeoNames() {
    }

    public GeoNames(String countryCode, String postalCode, String placeName, String adminName1, String adminCode1, String adminName2, String adminCode2, String adminName3, String adminCode3, String latitude, String longitude, String accuracy) {
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.placeName = placeName;
        this.adminName1 = adminName1;
        this.adminCode1 = adminCode1;
        this.adminName2 = adminName2;
        this.adminCode2 = adminCode2;
        this.adminName3 = adminName3;
        this.adminCode3 = adminCode3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public String getAdminName2() {
        return adminName2;
    }

    public void setAdminName2(String adminName2) {
        this.adminName2 = adminName2;
    }

    public String getAdminCode2() {
        return adminCode2;
    }

    public void setAdminCode2(String adminCode2) {
        this.adminCode2 = adminCode2;
    }

    public String getAdminName3() {
        return adminName3;
    }

    public void setAdminName3(String adminName3) {
        this.adminName3 = adminName3;
    }

    public String getAdminCode3() {
        return adminCode3;
    }

    public void setAdminCode3(String adminCode3) {
        this.adminCode3 = adminCode3;
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

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

}
