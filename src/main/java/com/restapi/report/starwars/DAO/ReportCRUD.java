package com.restapi.report.starwars.DAO;

import com.restapi.report.starwars.model.Report;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableAutoConfiguration
public interface ReportCRUD extends CrudRepository<Report, String> {
    List<Report> findAll();
    Optional<Report> findByReportId(String reportId);

    Report save(Report report);
}