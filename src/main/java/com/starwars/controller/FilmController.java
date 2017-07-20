package com.starwars.controller;

import com.starwars.model.Film;
import com.starwars.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RepositoryRestController
@Slf4j
public class FilmController {

    private FilmRepository filmRepository;
    private static HashMap<Integer, String> imdbLinks;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
        FilmController.loadImdbLinks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "films/search/findAllByReleaseDateGreaterThanEqual")
    public @ResponseBody ResponseEntity withIMDBLink(@RequestParam("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date releaseDate) {

        List<Film> films = filmRepository.findAllByReleaseDateGreaterThanEqual(releaseDate);

        Resources resources = new Resources(films);

        log.info("EStoy aqui");

        resources.forEach( resource -> {
            String url = FilmController.imdbLinks.get(((Film) resource).getEpisodeId());
            log.info("Link de la peli con episodio: " + ((Film) resource).getEpisodeId() + " - " + url);
            resources.add(new Link(url).withRel("imdb"));
        });

        return ResponseEntity.ok( resources );
    }

    private static void loadImdbLinks() {

        imdbLinks = new HashMap<>();

        imdbLinks.put( 1, "http://www.imdb.com/title/tt0120915/?ref_=fn_al_tt_1" );
        imdbLinks.put( 2, "http://www.imdb.com/title/tt0121765/?ref_=fn_al_tt_7" );
        imdbLinks.put( 3, "http://www.imdb.com/title/tt0121766/?ref_=fn_al_tt_6" );
        imdbLinks.put( 4, "http://www.imdb.com/title/tt0076759/?ref_=fn_al_tt_3" );
        imdbLinks.put( 5, "http://www.imdb.com/title/tt0080684/?ref_=fn_al_tt_5" );
        imdbLinks.put( 6, "http://www.imdb.com/title/tt0086190/?ref_=fn_al_tt_4" );
    }
}
