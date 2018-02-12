/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.controller;

import com.cdac.entities.GeoLiteCity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Suhas
 */
@Controller
public class BaseController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = {"/"})
    public ModelAndView getIndexView() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = {"/geo_light_city/{country}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<GeoLiteCity> getCitiesForCountry(@PathVariable String country) {
        return getCitiesListForCountry(country);
    }

    @RequestMapping(value = {"/geo_light_lat/{country}/{city}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<GeoLiteCity> getPostalsForCity(@PathVariable String country, @PathVariable String city) {
        return getPostalCode(country, city);
    }

    @RequestMapping(value = {"/geo_light_lat/{country}/{city}/{postal_code}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public GeoLiteCity getLatLogForPostal(@PathVariable String country, @PathVariable String city, @PathVariable String postal_code) {
        return getLatLongForPostalCode(country, city, postal_code);
    }

    @RequestMapping("/geo_light")
    @Transactional
    public ModelAndView getGeoLightView() {

        /*System.out.println(">>>>>>>>>> " + sessionFactory);
        helper.getGeoLiteCitys().forEach(geoLight -> {
            try {

                System.out.println("~~~~~ " + sessionFactory.getCurrentSession().save(geoLight));
                System.out.println(">>>>>>>>>> " + geoLight.getLocId());
            } catch (Exception ex) {
                System.out.println(">>>>> ~~~~~~~" + ex.getMessage());
                ex.printStackTrace();
            }
        });*/
        ModelAndView mv = new ModelAndView("geo_ligth");
        mv.addObject("geolightCountry", getDistictCountry());
        return mv;
    }

    private List<GeoLiteCity> getDistictCountry() {
        List geoLiteCitys = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoLiteCity.class);
        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("country"), "country")));

        geoLiteCitys = criteria.list();
        System.out.println(">>>>> " + geoLiteCitys.size());
        return geoLiteCitys;
    }

    private List<GeoLiteCity> getCitiesListForCountry(String country) {
        List geoLiteCitys = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoLiteCity.class);
        criteria.add(Restrictions.eq("country", country));

        geoLiteCitys = criteria.list();
        System.out.println(">>>>> " + geoLiteCitys.size());
        return geoLiteCitys;
    }

    private GeoLiteCity getLatLongForPostalCode(String country, String city, String postal_code) {
        List<GeoLiteCity> geoLiteCitys = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoLiteCity.class);
        //criteria.add(Restrictions.eq("country", country));
        //criteria.add(Restrictions.eq("city", city));
        criteria.add(Restrictions.eq("postalCode", postal_code));

        geoLiteCitys = criteria.list();
        System.out.println(">>>>> " + geoLiteCitys.get(0).getCity());
        return geoLiteCitys.get(0);
    }

    private List<GeoLiteCity> getPostalCode(String country, String city) {
        List<GeoLiteCity> geoLiteCitys = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoLiteCity.class);
        criteria.add(Restrictions.eq("country", country));
        criteria.add(Restrictions.eq("city", city));

        geoLiteCitys = criteria.list();
        System.out.println(">>>>> " + geoLiteCitys.size());
        return geoLiteCitys;
    }

    public static void main(String[] args) {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\CDAC-Data\\WORK\\2018.02.05_geolight\\geo_names\\IN.txt"));
            while ((line = br.readLine()) != null) {
                System.out.println(">>" + line.split("	")[0]);
                System.out.println(">>" + line.split("	")[1]);
                System.out.println(">>" + line.split("	")[2]);
                System.out.println(">>" + line.split("	")[3]);
                System.out.println(">>" + line.split("	")[4]);
                System.out.println(">>" + line.split("	")[5]);
                System.out.println(">>" + line.split("	")[6]);
                System.out.println(">>" + line.split("	")[7]);
                System.out.println(">>" + line.split("	")[8]);
                System.out.println(">>" + line.split("	")[9]);
                System.out.println(">>" + line.split("	")[10]);
                System.out.println(">>" + line.split("	")[11]);
                System.out.println("*********************************************************");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
