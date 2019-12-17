package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "report")
@Table(name = "report")
@JsonPropertyOrder({
        "reportId",
        "query_criteria_character_phrase",
        "query_criteria_planet_name",
        "result"
})
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    @JsonProperty("report_id")
    private String reportId;
    @Column()
    @NotNull
    @JsonProperty("query_criteria_character_phrase")
    private String queryCriteriaCharacterPhrase;
    @Column()
    @NotNull
    @JsonProperty("query_criteria_planet_name")
    private String queryCriteriaPlanetName;

    @OneToMany(targetEntity = Result.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "reportId")
    private List<Result> result = null;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public void addResult(Result result) {
        this.result.add(result);
    }
}