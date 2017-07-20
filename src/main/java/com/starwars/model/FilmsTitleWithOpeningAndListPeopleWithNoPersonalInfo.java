package com.starwars.model;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "filmsTitleWithOpeningAndListPeopleWithNoPersonalInfo", types = Film.class)
public interface FilmsTitleWithOpeningAndListPeopleWithNoPersonalInfo {

    String getTitle();
    String getOpeningCrawl();
    List<PeopleWithNoPersonalInfo> getPeople();
}
