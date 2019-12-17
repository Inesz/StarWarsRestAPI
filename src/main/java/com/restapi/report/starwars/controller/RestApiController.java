package com.restapi.report.starwars.controller;

import com.restapi.report.starwars.DAO.ReportCRUD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
    private Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    private ReportCRUD reportCRUD;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getString() {
        logger.info("Hello! Rest API method");
        return new ResponseEntity<String>("Hello! It is init Star Wars Rest API method", new HttpHeaders(), HttpStatus.OK);
    }
}