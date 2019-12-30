package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DB table model (@Column, @OneToMany annotations).
 * JSON deserializer model (@JsonProperty annotation).
 */
@Entity(name = "planet")
@Table(name = "planet")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Planet {
    private static final long serialVersionUID = -1798070006993154600L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(targetEntity = Person.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private List<Person> people = new ArrayList<>();
    @JsonProperty("name")
    @Column
    private String name;
    @JsonProperty("url")
    @Column
    private String url;
    @JsonProperty("residents")
    @Transient
    private List<String> residentsUrl = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("residents")
    public List<String> getResidentsUrl() {
        return residentsUrl;
    }

    @JsonProperty("residents")
    public void setResidentsUrl(List<String> residents) {
        this.residentsUrl = residents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addResident(Person person) {
        this.people.add(person);
    }

    @Override
    public String toString() {
        return "[" + getId() + " " + getName() + " " + getUrl() + " {" + getResidentsUrl() + "} " + getPeople() + "]";
    }
}