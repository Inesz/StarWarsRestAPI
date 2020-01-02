package com.restapi.report.starwars.controller;

import com.restapi.report.starwars.dao.ReportCRUD;
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
    private String TestCase1QueryCriteriaCharacterPhrase = "o";
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
    public void test1UpdateReportCheckResponseStatus_correctParameters_status204() {
        RequestBodyReport body = new RequestBodyReport();
        body.setQueryCriteriaCharacterPhrase(TestCase1QueryCriteriaCharacterPhrase);
        body.setQueryCriteriaPlanetName(TestCase1QueryCriteriaPlanetName);

        webTestClient
                .put()
                .uri("/report/" + TestCase1ReportId)
                .body(BodyInserters.fromObject(body))
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .isEmpty();
    }

    @Test
    public void test2updateReportCheckResponseStatus_incorrectParameters_status500() {
        RequestBodyReport body = new RequestBodyReport();

        webTestClient
                .put()
                .uri("/report/" + TestCase1ReportId)
                .body(BodyInserters.fromObject(body))
                .exchange()
                .expectStatus()
                .isBadRequest();
    }


    @Test
    public void test3getReportCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .get()
                .uri("/report/" + TestCase1ReportId)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test4getReportCheckResponse_reportInDB_status200andResultNodeNotEmpty() {
        test1UpdateReportCheckResponseStatus_correctParameters_status204();

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
    public void test5getReportsCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .get()
                .uri("/report")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test6deleteReportCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .delete()
                .uri("/report/" + TestCase1ReportId)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test7deleteReportsCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .delete()
                .uri("/report")
                .exchange()
                .expectStatus()
                .isOk();
    }
}