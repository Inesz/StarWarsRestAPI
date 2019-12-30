package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * HTTP RequestBody model.
 */
public class RequestBodyReport {
    @NotNull
    @JsonProperty("query_criteria_character_phrase")
    private String queryCriteriaCharacterPhrase;
    @NotNull
    @JsonProperty("query_criteria_planet_name")
    private String queryCriteriaPlanetName;

    public String getQueryCriteriaPlanetName() {
        return queryCriteriaPlanetName;
    }

    public void setQueryCriteriaPlanetName(String queryCriteriaPlanetName) {
        this.queryCriteriaPlanetName = queryCriteriaPlanetName;
    }

    public String getQueryCriteriaCharacterPhrase() {
        return queryCriteriaCharacterPhrase;
    }

    public void setQueryCriteriaCharacterPhrase(String queryCriteriaCharacterPhrase) {
        this.queryCriteriaCharacterPhrase = queryCriteriaCharacterPhrase;
    }
}