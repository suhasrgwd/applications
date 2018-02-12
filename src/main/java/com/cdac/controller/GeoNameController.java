/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.controller;

import com.cdac.entities.GeoNames;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang.StringEscapeUtils;
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
public class GeoNameController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/geo_names")
    @Transactional
    public ModelAndView getGeoNamesPage() {
        ModelAndView mv = new ModelAndView("geo_names");
        /*String line = "";
        GeoNames geoNames = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\CDAC-Data\\WORK\\2018.02.05_geolight\\geo_names\\IN.txt"));
            while ((line = br.readLine()) != null) {
                String[] srt = line.split("	");
                geoNames = new GeoNames(srt[0], srt[1], srt[2], srt[3], srt[4], srt[5], srt[6], srt[7], srt[8], srt[9], srt[10], srt[11]);
                sessionFactory.getCurrentSession().save(geoNames);
                System.out.println(">>" + srt[0]);
                System.out.println(">>" + srt[1]);
                System.out.println(">>" + srt[2]);
                System.out.println(">>" + srt[3]);
                System.out.println(">>" + srt[4]);
                System.out.println(">>" + srt[5]);
                System.out.println(">>" + srt[6]);
                System.out.println(">>" + srt[7]);
                System.out.println(">>" + srt[8]);
                System.out.println(">>" + srt[9]);
                System.out.println(">>" + srt[10]);
                System.out.println(">>" + srt[11]);
                System.out.println("*********************************************************");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        mv.addObject("state_names", getDistictStates());
        return mv;
    }

    @RequestMapping(value = {"/geo_names/{state}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<GeoNames> getDistricts_(@PathVariable String state) {
        return getDistrict(state);
    }

    @RequestMapping(value = {"/geo_names/{state}/{district}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<GeoNames> getTalukas_(@PathVariable String state, @PathVariable String district) {
        return getTalukas(state, district);
    }

    @RequestMapping(value = {"/geo_names/{state}/{district}/{taluka}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<GeoNames> getPlaces_(@PathVariable String state, @PathVariable String district, @PathVariable String taluka) {
        return getPlaces(state, district, taluka);
    }

    @RequestMapping(value = {"/geo_names/{state}/{district}/{taluka}/{place}"}, method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public GeoNames getPlaces_(@PathVariable String state, @PathVariable String district, @PathVariable String taluka, @PathVariable String place) {
        System.out.println(state + " " + district + " " + taluka + " " + place);
        return getCordinates(state, district, taluka, place);
    }

    private List<GeoNames> getDistictStates() {
        List geoNames = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoNames.class);
        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("adminName1"), "adminName1")));

        geoNames = criteria.list();
        System.out.println(">>>>> " + geoNames.size());
        return geoNames;
    }

    private List<GeoNames> getDistrict(String state) {
        List<GeoNames> geoNames = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoNames.class);
        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("adminName2"), "adminName2")));
        criteria.add(Restrictions.eq("adminName1", state));

        geoNames = criteria.list();
        System.out.println(">>>>> " + geoNames.size());
        return geoNames;
    }

    private List<GeoNames> getTalukas(String state, String district) {
        List<GeoNames> geoNames = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoNames.class);
        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("adminName3"), "adminName3")));
        criteria.add(Restrictions.eq("adminName1", state));
        criteria.add(Restrictions.eq("adminName2", district));

        geoNames = criteria.list();
        System.out.println(">>>>> " + geoNames.size());
        return geoNames;
    }

    private List<GeoNames> getPlaces(String state, String district, String taluka) {
        List<GeoNames> geoNames = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoNames.class);
        criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("placeName"), "placeName")));
        criteria.add(Restrictions.eq("adminName1", state));
        criteria.add(Restrictions.eq("adminName2", district));
        criteria.add(Restrictions.eq("adminName3", taluka));

        geoNames = criteria.list();
        System.out.println(">>>>> " + geoNames.size());
        return geoNames;
    }

    private GeoNames getCordinates(String state, String district, String taluka, String place) {
        List<GeoNames> geoNames = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(GeoNames.class);
        criteria.add(Restrictions.eq("adminName1", state));
        criteria.add(Restrictions.eq("adminName2", district));
        criteria.add(Restrictions.eq("adminName3", taluka));
        criteria.add(Restrictions.eq("placeName", StringEscapeUtils.escapeHtml(place)));

        geoNames = criteria.list();
        System.out.println(">>>>> " + geoNames.size());
        return geoNames.get(0);
    }
}
