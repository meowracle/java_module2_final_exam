package com.codegym.services;

import com.codegym.models.City;
import com.codegym.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Page<City> findAll(Pageable pageable);

    City findById(Long id);

    void save (City city);

    void delete (Long id);

    Iterable<City> findAllByCountry(Country country);

    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
