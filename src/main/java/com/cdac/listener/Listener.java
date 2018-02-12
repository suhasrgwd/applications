/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.listener;

import com.cdac.conf.Helper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.ServletContextAttributeExporter;

/**
 *
 * @author Suhas
 */
@Configuration
public class Listener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired(required = true)
    private Helper helper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        System.out.println("Info: >> Application ContextRefreshed Event Called.");
    }

    @PostConstruct
    public void onInit() {
        System.out.println("Info: >> PostConstruct onInit function Called.");
        //helper.setGeoLiteCitys(helper.readCSV("D:\\CDAC-Data\\WORK\\2018.02.05_geolight\\GeoLiteCity_20180102\\GeoLiteCity-Location_1.csv"));
        //System.out.println(">>>>>>>>>>>>>> *********************" + helper.getGeoLiteCitys().size());
        helper.generateIniitialIndex();
    }

    @Bean
    public ServletContextAttributeExporter servletContextAttributeExporter() {
        Map<String, Object> map = new HashMap();
        map.put("helper", new Helper());
        ServletContextAttributeExporter exporter = new ServletContextAttributeExporter();
        exporter.setAttributes(map);
        return exporter;
    }

}
