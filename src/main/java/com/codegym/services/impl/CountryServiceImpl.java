package com.codegym.services.impl;

import com.codegym.models.Country;
import com.codegym.repositories.CountryRepository;
import com.codegym.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;


    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }
}
