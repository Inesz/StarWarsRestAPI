package com.restapi.report.starwars.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restapi.report.starwars.serializer.ReportJacksonSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * DB table model.
 * Main Report entity.
 * The basis for report serialization.
 */
@Entity(name = "report")
@Table(name = "report")
@JsonSerialize(using = ReportJacksonSerializer.class)
public class Report {
    private static final long serialVersionUID = -1700070786993154600L;

    @Id
    @Column(name = "id")
    @NotNull
    private String reportId;
    @Column()
    @NotNull
    private String queryCriteriaCharacterPhrase;
    @Column()
    @NotNull
    private String queryCriteriaPlanetName;
    @OneToOne(targetEntity = Planet.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Planet planet = null;

    public Report() {
    }

    public Report(String reportId, String queryCriteriaCharacterPhrase, String queryCriteriaPlanetName) {
        this.reportId = reportId;
        this.queryCriteriaCharacterPhrase = queryCriteriaCharacterPhrase;
        this.queryCriteriaPlanetName = queryCriteriaPlanetName;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getQueryCriteriaCharacterPhrase() {
        return queryCriteriaCharacterPhrase;
    }

    public void setQueryCriteriaCharacterPhrase(String queryCriteriaCharacterPhrase) {
        this.queryCriteriaCharacterPhrase = queryCriteriaCharacterPhrase;
    }

    public String getQueryCriteriaPlanetName() {
        return queryCriteriaPlanetName;
    }

    public void setQueryCriteriaPlanetName(String queryCriteriaPlanetName) {
        this.queryCriteriaPlanetName = queryCriteriaPlanetName;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Override
    public String toString(){
        return "[" + getReportId() + " " + getQueryCriteriaCharacterPhrase() + " " + getQueryCriteriaPlanetName() + " " + getPlanet() + "]";
    }
}