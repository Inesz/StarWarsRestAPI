package com.restapi.report.starwars.controller;

import com.restapi.report.starwars.DAO.ReportCRUD;
import com.restapi.report.starwars.model.RequestBodyReport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class RestApiControllerTest {
    private String TestCase1ReportId = "1";
    private String TestCase1QueryCriteriaCharacterPhrase = "a";
    private String TestCase1QueryCriteriaPlanetName = "Naboo";


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReportCRUD reportCRUD;

    @AfterEach
    public void cleanDB() {
        reportCRUD.deleteAll();
    }

    @Test
    public void updateReport() {
        RequestBodyReport body = new RequestBodyReport();
        body.setQueryCriteriaCharacterPhrase(TestCase1QueryCriteriaCharacterPhrase);
        body.setQueryCriteriaPlanetName(TestCase1QueryCriteriaPlanetName);

        webTestClient
                .put()
                .uri("/report/" + TestCase1ReportId)
                .body(BodyInserters.fromObject(body))
                .exchange()
                .expectStatus()
                .isNoContent();
    }


    @Test
    public void getReport() {
        webTestClient
                .get()
                .uri("/report/" + TestCase1ReportId)
                .exchange()
                .expectStatus()
                .isOk();

        updateReport();

        webTestClient
                .get()
                .uri("/report/" + TestCase1ReportId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.result")
                .isNotEmpty();
    }

    @Test
    public void getReports() {
        webTestClient
                .get()
                .uri("/report")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteReport() {
        webTestClient
                .delete()
                .uri("/report/" + TestCase1ReportId)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteReports() {
        webTestClient
                .delete()
                .uri("/report")
                .exchange()
                .expectStatus()
                .isOk();
    }
}