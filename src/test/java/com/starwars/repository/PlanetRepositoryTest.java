package com.starwars.repository;

import com.starwars.model.Planet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void should_find_by_name() throws Exception {

        Planet alderaan = this.planetRepository.findByName( "Alderaan" );

        assertThat( alderaan.getName(), is("Alderaan"));
    }

    @Test
    public void should_find_all_paging() {

        PageRequest page = new PageRequest( 0, 2);
        Page<Planet> all = this.planetRepository.findAll(page);

        Sort sort = new Sort(Sort.Direction.ASC, "climate")
                .and(new Sort(Sort.Direction.DESC, "terrain"));

        this.planetRepository.findAll(sort);

        System.out.println("test");

    }
}
