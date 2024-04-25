package com.cristian.project.api.service;

import com.cristian.project.api.dto.CovidCasesRequest;
import com.cristian.project.api.exceptions.custom.GenericException;

public interface CountryService {

    CovidCasesRequest SearchNameCountry(String name) throws GenericException;

    void saveCountryCases(String name);

    void saveCountryDeaths(String name);

    void deleteCountryByName(String name);


// save, search


}
//