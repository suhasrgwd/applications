/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.conf;

import com.cdac.entities.GeoLiteCity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Suhas
 */
@Component
public class Helper {

    private List<GeoLiteCity> geoLiteCitys;

    public List<GeoLiteCity> getGeoLiteCitys() {
        return geoLiteCitys;
    }

    public void setGeoLiteCitys(List<GeoLiteCity> geoLiteCitys) {
        this.geoLiteCitys = geoLiteCitys;
    }

    public Helper() {
    }

    /*public static Connection getCollection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/geo_lite_city", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return con;
    }*/
    @Autowired
    SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void generateIniitialIndex() {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public List<GeoLiteCity> readCSV(String csvFile) {
        List<GeoLiteCity> gwo_lite = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        String[] gwo_row = null;

        try (//Connection con = getCollection();
                BufferedReader br = new BufferedReader(new FileReader(csvFile));) {

            double i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                gwo_row = line.split(cvsSplitBy);
                System.out.println("Line Number: " + i + " Line Length: " + gwo_row.length);

                switch (gwo_row.length) {
                    case 7:
                        gwo_lite.add(new GeoLiteCity(Integer.parseInt(gwo_row[0]), gwo_row[1], gwo_row[2], gwo_row[3], gwo_row[4], gwo_row[5], gwo_row[6], "", ""));
                        break;
                    case 8:
                        gwo_lite.add(new GeoLiteCity(Integer.parseInt(gwo_row[0]), gwo_row[1], gwo_row[2], gwo_row[3], gwo_row[4], gwo_row[5], gwo_row[6], gwo_row[7], ""));
                        break;
                    case 9:
                        gwo_lite.add(new GeoLiteCity(Integer.parseInt(gwo_row[0]), gwo_row[1], gwo_row[2], gwo_row[3], gwo_row[4], gwo_row[5], gwo_row[6], gwo_row[7], gwo_row[8]));
                        break;
                    default:
                        break;
                }
            }
            //con.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gwo_lite;
    }
}
