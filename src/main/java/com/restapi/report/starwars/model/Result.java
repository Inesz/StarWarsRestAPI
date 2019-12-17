package com.restapi.report.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "result")
@Table(name = "result")
@JsonPropertyOrder({
        "film_id",
        "film_name",
        "character_id",
        "character_name",
        "planet_id",
        "planet_name"
})
public class Result {
    @Id
    @Column
    @JsonIgnore
    private String reportId;
    @Column
    @JsonProperty("film_id")
    private String filmId;
    @Column
    @JsonProperty("film_name")
    private String filmName;
    @Column
    @JsonProperty("character_id")
    private String characterId;
    @Column
    @JsonProperty("character_name")
    private String characterName;
    @Column
    @JsonProperty("planet_id")
    private String planetId;
    @Column
    @JsonProperty("planet_name")
    private String planetName;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getPlanetId() {
        return planetId;
    }

    public void setPlanetId(String planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}
