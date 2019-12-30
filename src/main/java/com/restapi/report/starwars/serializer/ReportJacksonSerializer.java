package com.restapi.report.starwars.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.restapi.report.starwars.model.Report;
import com.restapi.report.starwars.model.Film;
import com.restapi.report.starwars.model.Person;

import java.io.IOException;

/**
 * Custom Jackson serializer for Report entity.
 */
public class ReportJacksonSerializer extends StdSerializer<Report> {

    public ReportJacksonSerializer() {
        this(null);
    }

    protected ReportJacksonSerializer(Class<Report> t) {
        super(t);
    }

    @Override
    public void serialize(Report value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("report_id", value.getReportId());
        gen.writeStringField("query_criteria_character_phrase", value.getQueryCriteriaCharacterPhrase());
        gen.writeStringField("query_criteria_planet_name", value.getQueryCriteriaPlanetName());
        gen.writeArrayFieldStart("result");
        for (Person resident : value.getPlanet().getPeople()) {
            for (Film film : resident.getFilms()) {
                gen.writeStartObject();
                gen.writeStringField("film_id", film.getUrl());
                gen.writeStringField("film_name", film.getTitle());
                gen.writeStringField("character_id", resident.getUrl());
                gen.writeStringField("character_name", resident.getName());
                gen.writeStringField("planet_id", value.getPlanet().getUrl());
                gen.writeStringField("planet_name", value.getPlanet().getName());
                gen.writeEndObject();
            }
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}