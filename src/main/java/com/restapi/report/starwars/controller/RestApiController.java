package com.restapi.report.starwars.controller;

import com.restapi.report.starwars.model.Report;
import com.restapi.report.starwars.model.RequestBodyReport;
import com.restapi.report.starwars.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestApiController {
    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/")
    public ResponseEntity<String> getString() {
        return new ResponseEntity<>("Hello! It is init Star Wars Rest API method", new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Creates report with a given "reportId" and requestBodyReport parameters.
     * Inserts to DB or updates if report of given reportId exists.
     * <p>
     * Raport contains a list of films in which appeared characters who contains given CHARACTER_PHRASE ​
     * in their name and whose homeworld planet is ​PLANET_NAME
     * Data is retrieved from an external service.
     *
     * @param reportId          unique report id.
     * @param requestBodyReport PUT HTTP method body:
     *                          {
     *                          "query_criteria_character_phrase": "PHRASE",
     *                          "query_criteria_planet_name": "NAME"
     *                          }
     * @return HttpStatus.NO_CONTENT in case of exit without error or HttpStatus.BAD_REQUEST in case of a body validation error.
     */
    @PutMapping(value = "/report/{reportId}", consumes = "application/json")
    public ResponseEntity updateReport(@PathVariable("reportId") String reportId, @Valid @RequestBody RequestBodyReport requestBodyReport) {

        reportService.updateReport(
                reportId,
                requestBodyReport.getQueryCriteriaCharacterPhrase(),
                requestBodyReport.getQueryCriteriaPlanetName());

        return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    /**
     * Return report with given "reportId".
     *
     * @param reportId
     * @return JSON in ReportJacksonSerializer format:
     * {@code
     * {
     * “report_id”: “{report_id},
     * “query_criteria_character_phrase”: “CHARACTER_PHRASE”,
     * “query_criteria_planet_name”: “PLANET_NAME”,
     * “result”: [{
     * “film_id”: “FILM_ID”,
     * “film_name”: “FILM_NAME”,
     * “character_id”: “CHARACTER_ID”,
     * “character_name”: “CHARACTER_NAME”,
     * “planet_id”: “PLANET_ID”,
     * “planet_name”: “PLANET_NAME” }]}
     * }
     */
    @GetMapping(value = "/report/{reportId}")
    public ResponseEntity getReport(@PathVariable("reportId") String reportId) {

        Report report = reportService.getReport(reportId);

        return new ResponseEntity<>(report, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Return all reports from DB
     *
     * @return JSON in ReportJacksonSerializer format:
     * {@code
     * [{
     * “report_id”: “{report_id},
     * “query_criteria_character_phrase”: “CHARACTER_PHRASE”,
     * “query_criteria_planet_name”: “PLANET_NAME”,
     * “result”: [{
     * “film_id”: “FILM_ID”,
     * “film_name”: “FILM_NAME”,
     * “character_id”: “CHARACTER_ID”,
     * “character_name”: “CHARACTER_NAME”,
     * “planet_id”: “PLANET_ID”,
     * “planet_name”: “PLANET_NAME” }]},
     * ...]
     * }
     */
    @GetMapping(value = "/report")
    public ResponseEntity getReports() {

        List<Report> reports = reportService.getReports();

        return new ResponseEntity<>(reports, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete report with given "reportId" from DB.
     *
     * @param reportId
     * @return
     */
    @DeleteMapping(value = "/report/{reportId}")
    public ResponseEntity deleteReport(@PathVariable("reportId") String reportId) {

        reportService.deleteReport(reportId);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete all reports from DB.
     *
     * @return
     */
    @DeleteMapping(value = "/report")
    public ResponseEntity deleteReports() {

        reportService.deleteReports();

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

}