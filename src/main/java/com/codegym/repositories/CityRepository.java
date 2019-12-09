package com.codegym.repositories;

import com.codegym.models.City;
import com.codegym.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    Iterable<City> findAllByCountry (Country country);

    Page<City> findAllByNameContaining(String name, Pageable pageable);

}
