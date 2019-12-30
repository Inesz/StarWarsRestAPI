package com.restapi.report.starwars.DAO;

import com.restapi.report.starwars.model.Film;
import com.restapi.report.starwars.model.Person;
import com.restapi.report.starwars.model.Planet;
import com.restapi.report.starwars.model.SwapiResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Configuration
@ComponentScan
public class SwapiRestConsume {
    private static final Logger LOGGER = LoggerFactory.getLogger(SwapiRestConsume.class);
    private static final String SWAPI_BASE_URL = "https://swapi.co/api/";
    private static final String SWAPI_PLANETS_URI = "planets/";
    private static final String SWAPI_SEARCH_PARAMETER = "?search=";

    private final WebClient webClient;

    public SwapiRestConsume(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .filters(exchangeFilterFunctions -> exchangeFilterFunctions.add(logRequest()))
                .baseUrl(SWAPI_BASE_URL)
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (LOGGER.isInfoEnabled()) {
                String message = MessageFormat.format("Request: {0}", clientRequest.url());
                LOGGER.info(message);
            }
            return Mono.just(clientRequest);
        });
    }

    /**
     * Get Planet with given searchPlanetName.
     *
     * @param searchPlanetName full planet "name"
     * @return Planet or null
     */
    public Planet getPlanet(String searchPlanetName) {
        try {
            return filterPlanet(getPlanets(searchPlanetName), searchPlanetName);
        } catch (Exception e) {
            LOGGER.info("Planet get error:", e);
            return null;
        }
    }

    private SwapiResults<Planet> getPlanets(String searchPlanets) {
        ParameterizedTypeReference<SwapiResults<Planet>> swapiResultsPlanet
                = new ParameterizedTypeReference<SwapiResults<Planet>>() {
        };

        try {
            return webClient
                    .get()
                    .uri(SWAPI_PLANETS_URI + SWAPI_SEARCH_PARAMETER + searchPlanets)
                    .retrieve()
                    .bodyToMono(swapiResultsPlanet)
                    .block();
        } catch (Exception e) {
            LOGGER.info("Planet retrieve error:", e);
            return null;
        }
    }

    private Planet filterPlanet(SwapiResults<Planet> planets, String searchPlanetName) {
        return planets.getResults()
                .stream()
                .filter(planet -> searchPlanetName.equalsIgnoreCase(planet.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get Person from "url" if person name contains "searchPerson" phrase.
     *
     * @param url          url to person
     * @param searchPerson full or part of person name
     * @return Person or null
     */
    public Person getPerson(String url, String searchPerson) {
        try {
            return webClient
                    .get()
                    .uri(url + SWAPI_SEARCH_PARAMETER + searchPerson)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get Film from "url"
     *
     * @param url to film
     * @return Film or null
     */
    public Film getFilm(String url) {
        try {
            return webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Film.class)
                    .block();
        } catch (Exception e) {
            LOGGER.info("Film retrieve error:", e);
            return null;
        }
    }
}