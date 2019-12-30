package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DB table model (@Column, @ManyToMany annotations).
 * JSON deserializer model (@JsonProperty annotation).
 */
@Entity(name = "person")
@Table(name = "person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(targetEntity = Film.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    @Fetch(FetchMode.SELECT)
    private List<Film> films = new ArrayList<>();
    @JsonProperty("name")
    @Column
    private String name;
    @JsonProperty("films")
    @Transient
    private List<String> filmsUrl = null;
    @JsonProperty("url")
    @Column
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("films")
    public List<String> getFilmsUrl() {
        return filmsUrl;
    }

    @JsonProperty("films")
    public void setFilmsUrl(List<String> filmsUrl) {
        this.filmsUrl = filmsUrl;
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        if (film != null) this.films.add(film);
    }

    @Override
    public String toString() {
        return "[" + getId() + " " + getName() + " " + getUrl() + " {" + getFilmsUrl() + "} " + getFilms() + "]";
    }
}