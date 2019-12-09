package com.codegym.services;

import com.codegym.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Page<Country> findAll(Pageable pageable);

    Country findById(Long id);

    void save (Country country);

    void delete(Long id);
}
