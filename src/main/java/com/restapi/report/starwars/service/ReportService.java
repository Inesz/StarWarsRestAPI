package com.restapi.report.starwars.service;

import com.restapi.report.starwars.dao.ReportCRUD;
import com.restapi.report.starwars.dao.SwapiRestConsume;
import com.restapi.report.starwars.model.Film;
import com.restapi.report.starwars.model.Person;
import com.restapi.report.starwars.model.Planet;
import com.restapi.report.starwars.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private SwapiRestConsume swapiRest;

    @Autowired
    private ReportCRUD reportCRUD;

    /**
     * Create report and insert to DB or update DB.
     *
     * @param reportId        unique reportId
     * @param characterPhrase full or part of person name phrase
     * @param planetName      full planet name
     */
    public void updateReport(String reportId, String characterPhrase, String planetName) {
        Report report = createReport(reportId, characterPhrase, planetName);
        deleteReport(reportId);
        reportCRUD.save(report);
    }

    /**
     * Create report.
     *
     * @param reportId        unique reportId
     * @param characterPhrase full or part of person name phrase
     * @param planetName      full planet name
     * @return fully built Report or null
     */
    private Report createReport(String reportId, String characterPhrase, String planetName) {
        Report report = new Report(reportId, characterPhrase, planetName);

        addPlanet(report, report.getQueryCriteriaPlanetName());
        if (report.getPlanet() == null) return null;

        addPeople(report.getPlanet(), report.getQueryCriteriaCharacterPhrase());
        if (report.getPlanet().getPeople() == null) return null;

        addFilms(report.getPlanet().getPeople());
        if (report.getPlanet().getPeople().get(0).getFilms() == null) return null;

        return report;
    }

    /**
     * Set Planet to Report.
     *
     * @param report
     * @param queryCriteriaPlanetName
     */
    private void addPlanet(Report report, String queryCriteriaPlanetName) {
        report.setPlanet(swapiRest.getPlanet(queryCriteriaPlanetName));
    }

    /**
     * Set people to Planet.
     *
     * @param planet
     * @param queryCriteriaCharacterPhrase
     */
    private void addPeople(Planet planet, String queryCriteriaCharacterPhrase) {
        for (String personUrl : planet.getResidentsUrl()) {
            Person person = swapiRest.getPerson(personUrl, queryCriteriaCharacterPhrase);

            if (person != null) planet.addResident(person);
        }
    }

    /**
     * Set Films to people.
     * The auxiliary HashMap "cacheFilms" stores Films to avoid duplication of requests.
     *
     * @param people
     */
    private void addFilms(List<Person> people) {
        Map<String, Film> cacheFilms = new HashMap<>();

        for (Person person : people) {
            for (String filmUrl : person.getFilmsUrl()) {
                person.addFilm(getFilm(filmUrl, cacheFilms));
            }
        }
    }

    /**
     * Get film from Map "films" or get film from "filmUrl".
     *
     * @param filmUrl
     * @param films
     * @return
     */
    private Film getFilm(String filmUrl, Map<String, Film> films) {
        Film film = films.get(filmUrl);

        if (film == null) {
            film = swapiRest.getFilm(filmUrl);
            if (film != null) films.put(filmUrl, film);
        }
        return film;
    }

    /**
     * Return Report with given "reportId" or null.
     *
     * @param reportId
     * @return
     */
    public Report getReport(String reportId) {
        return reportCRUD.findByReportId(reportId).orElse(null);
    }

    /**
     * Return all Reports.
     *
     * @return
     */
    public List<Report> getReports() {
        try {
            return reportCRUD.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Delete Report with given "reportId" from DB.
     *
     * @param reportId
     */
    public void deleteReport(String reportId) {
        if (reportCRUD.existsById(reportId)) {
            reportCRUD.deleteById(reportId);
        }
    }

    /**
     * Delete all Report from DB.
     */
    public void deleteReports() {
        reportCRUD.deleteAll();
    }
}