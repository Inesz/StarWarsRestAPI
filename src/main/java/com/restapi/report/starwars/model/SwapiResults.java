package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to deserialize swapi "results" node.
 * @param <T> eg. Planet
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwapiResults<T> {
    @JsonProperty("results")
    private List<T> results = new ArrayList<>();

    public List<T> getResults() {
        return results;
    }

    public void setResult(List<T> planets) {
        this.results = planets;
    }
}