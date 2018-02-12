
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.entities.hibernate_search;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Suhas
 */
@Entity
@Indexed
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //index=Index.YES, analyze=Analyze.YES and store=Store.NO are the default values for these parameters and could be omitted.
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String title;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String subTitle;

    /*This annotation is used to index associated entities (@ManyToMany, @*ToOne, @Embedded and @ElementCollection)
    Lucene index document is a flat data structure which does not know anything about object relations*/
    @IndexedEmbedded
    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    //Hibernate Search must convert the data types of the indexed fields to their respective Lucene encoding.
    @DateBridge(resolution = Resolution.DAY)
    private Date publicationDate;

    public Book() {
    }

    public Book(Integer id, String title, String subTitle, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

}
