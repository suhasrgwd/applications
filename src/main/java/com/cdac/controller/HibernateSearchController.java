/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.controller;

import com.cdac.entities.GeoNames;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Suhas
 */
@Controller//hibernate_search
public class HibernateSearchController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/hibernate_search")
    public ModelAndView getGeoLightView() {
        ModelAndView mv = new ModelAndView("hibernate_search");
        return mv;
    }

    @RequestMapping("/hibernate_search/{search_text}")
    @Transactional
    public void getSearchedResults(@PathVariable String search_text) {
        getSearchResuls(search_text);
    }

    @javax.transaction.Transactional
    public void getSearchResuls(String searchStr) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(GeoNames.class).get();
        Query query = qb
                .keyword()
                .onFields("placeName", "adminName1", "adminName2", "adminName3")
                .matching(searchStr)
                .createQuery();

        // wrap Lucene query in a org.hibernate.Query
        org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, GeoNames.class);
        List<GeoNames> resuls = hibQuery.list();
        System.out.println("********************");
        resuls.forEach(geoNames -> {
            System.out.println(geoNames.getPlaceName() + " ** " + geoNames.getAdminName1() + " " + geoNames.getAdminName3() + " " + geoNames.getAdminName3());
        });
        System.out.println("********************");
    }

}
