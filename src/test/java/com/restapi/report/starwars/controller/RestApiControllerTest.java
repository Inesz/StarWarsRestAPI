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
    private final String URI = "/report/";

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

        webTestClient
                .put()
                .uri(URI + Data.DATA1.getReportId())
                .body(BodyInserters.fromObject(Data.DATA1.getRequestBodyReport()))
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .isEmpty();
    }

    @Test
    public void test2getReportCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .get()
                .uri(URI + Data.DATA2.getReportId())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test3getReportCheckResponse_reportInDB_status200andResultNodeNotEmpty() {
        test1UpdateReportCheckResponseStatus_correctParameters_status204();

        webTestClient
                .get()
                .uri(URI + Data.DATA2.getReportId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.result")
                .isNotEmpty();
    }

    @Test
    public void test4getReportsCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .get()
                .uri(URI)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test5deleteReportCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .delete()
                .uri(URI + Data.DATA2.getReportId())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void test6deleteReportsCheckResponseStatus_noReportInDB_status200() {
        webTestClient
                .delete()
                .uri(URI)
                .exchange()
                .expectStatus()
                .isOk();
    }

    static class Data {
        static final DataModel DATA1 = new DataModel("1", "o", "Naboo");
        static final DataModel DATA2 = new DataModel("1");

        static class DataModel {
            String reportId;
            RequestBodyReport requestBodyReport;

            DataModel(String reportId) {
                this.reportId = reportId;
            }

            DataModel(String reportId, String queryCriteriaCharacterPhrase, String queryCriteriaPlanetName) {
                this.reportId = reportId;
                this.requestBodyReport = new RequestBodyReport();
                requestBodyReport.setQueryCriteriaCharacterPhrase(queryCriteriaCharacterPhrase);
                requestBodyReport.setQueryCriteriaPlanetName(queryCriteriaPlanetName);
            }

            String getReportId() {
                return reportId;
            }

            RequestBodyReport getRequestBodyReport() {
                return requestBodyReport;
            }
        }
    }
}