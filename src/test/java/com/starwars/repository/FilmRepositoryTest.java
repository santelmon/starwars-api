package com.starwars.repository;

import com.starwars.model.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void should_find_all_by_max_people() throws Exception {

        List<Film> peopleList = this.filmRepository.findAllByMaxPeople();

        assertNotNull( peopleList );
    }

    @Test
    public void should_print_all_films() {

        List<Film> all = this.filmRepository.findAll();

        //all.stream().forEach(System.out::println);
        all.stream().forEach(f -> filmRepository.logFilm(f));

    }
}
