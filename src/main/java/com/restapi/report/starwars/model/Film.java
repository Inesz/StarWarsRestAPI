package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * DB table model (@Column annotation).
 * JSON deserializer model (@JsonProperty annotation).
 */
@Entity(name = "film")
@Table(name = "film")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Film {
    private static final long serialVersionUID = -1798070786993154600L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @Column
    @JsonProperty("title")
    private String title;
    @Column
    @JsonProperty("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" + getId() + " " + getTitle() + " " + getUrl() + "]";
    }
}