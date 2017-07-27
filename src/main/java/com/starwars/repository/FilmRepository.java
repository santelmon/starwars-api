package com.starwars.repository;

import com.starwars.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, CustomFilmRepository {


    @RestResource(exported = false)
    @Override
    Film saveAndFlush(Film film);

    @RestResource(exported = false)
    @Override
    Film save(Film film);

    @RestResource(exported = false)
    @Override
    void deleteInBatch(Iterable<Film> iterable);

    @RestResource(exported = false)
    @Override
    void deleteAllInBatch();

    @RestResource(exported = false)
    @Override
    void delete(Long aLong);

    @RestResource(exported = false)
    @Override
    void delete(Film film);

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends Film> iterable);

    @RestResource(exported = false)
    @Override
    void deleteAll();

    List<Film> findByOrderByEpisodeIdAsc();
    List<Film> findAllByReleaseDateGreaterThanEqual(@Param("releaseDate") Date releaseDate);

    @Query("select f from Film f where f.people.size = (select max(f2.people.size) from Film f2)")
    List<Film> findAllByMaxPeople();

    @Query("select f from Film f where f.planets.size = (select min(f2.planets.size) from Film f2)")
    List<Film> findAllByMinPlanets();

    @Query("select f from Film f join f.people p where p.name = :name")
    List<Film> findAllByPeopleContains(@Param("name") String name);
}
