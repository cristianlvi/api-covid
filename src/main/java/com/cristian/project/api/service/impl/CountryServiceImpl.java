package com.cristian.project.api.service.impl;

import com.cristian.project.api.client.ApiNinjaClient;
import com.cristian.project.api.config.GsonConfig;
import com.cristian.project.api.dto.CovidDeathsRequest;
import com.cristian.project.api.dto.InfoRequest;
import com.cristian.project.api.dto.CovidCasesRequest;
import com.cristian.project.api.exceptions.custom.CountryException;
import com.cristian.project.api.exceptions.custom.GenericException;
import com.cristian.project.api.model.Case;
import com.cristian.project.api.model.Country;
import com.cristian.project.api.model.Death;
import com.cristian.project.api.repository.CaseRepository;
import com.cristian.project.api.repository.CountryRepository;
import com.cristian.project.api.repository.DeathRepository;
import com.cristian.project.api.service.CountryService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Value("${spring.token.header.ninja-api}")
    private String tokenApiNinja;

    private ApiNinjaClient apiNinjaClient;
    private GsonConfig gson;

    private CountryRepository countryRepository;
    private CaseRepository caseRepository;
    private DeathRepository deathRepository;

    public CountryServiceImpl(ApiNinjaClient apiNinjaClient, GsonConfig gson,
                              CountryRepository countryRepository, CaseRepository caseRepository,
                              DeathRepository deathRepository) {
        this.apiNinjaClient = apiNinjaClient;
        this.gson = gson;
        this.countryRepository = countryRepository;
        this.caseRepository = caseRepository;
        this.deathRepository = deathRepository;
    }

    @Override
    public CovidCasesRequest SearchNameCountry(String name) throws GenericException {
        String returnApiNinja = apiNinjaClient.searchNameCountry(name, tokenApiNinja);

        try {
            CovidCasesRequest[] covidData = gson.getGeson().fromJson(returnApiNinja, CovidCasesRequest[].class);

            return covidData[0];
        } catch (FeignException fe) {
            throw new GenericException("error integrate api");
        }
    }

    @Override
    public void saveCountryCases(String name) {
        String returnResponseApi = apiNinjaClient.searchNameCountry(name, tokenApiNinja);

        Optional<Country> countryOpt = countryRepository.findByNameContainingIgnoreCase(name);

        Long countryId;

        CovidCasesRequest[] covidCasesRequests = gson.getGeson().fromJson(returnResponseApi, CovidCasesRequest[].class);

        try {
            if (countryOpt.isPresent()) {

                countryId = countryOpt.get().getId();

                for (Map.Entry<String, InfoRequest> casesEntry : covidCasesRequests[0].getCases().entrySet()) {

                    Case cases = new Case();
                    cases.setDate(casesEntry.getKey());
                    cases.setTotal(casesEntry.getValue().getTotal());
                    cases.setNews(casesEntry.getValue().getCaseNews());
                    cases.setCountryId(countryId);

                    caseRepository.save(cases);
                }
            } else {
                Country country = covidCasesRequests[0].convertCountry(new Country());
                countryRepository.save(country);

                countryId = country.getId();

                for (Map.Entry<String, InfoRequest> casesEntry : covidCasesRequests[0].getCases().entrySet()) {

                    Case cases = new Case();
                    cases.setDate(casesEntry.getKey());
                    cases.setTotal(casesEntry.getValue().getTotal());
                    cases.setNews(casesEntry.getValue().getCaseNews());
                    cases.setCountryId(countryId);

                    caseRepository.save(cases);

                }
            }

        } catch (CountryException ce) {
            throw new CountryException("failure to save country and cases from covid19 in the database");
        }
    }


    @Override
    public void saveCountryDeaths(String name) {
        String returnResponseApi = apiNinjaClient.searchCasesDeathsCountry(name, tokenApiNinja);

        CovidDeathsRequest[] covidDeathsRequest = gson.getGeson().fromJson(returnResponseApi, CovidDeathsRequest[].class);

        Optional<Country> countryOpt = countryRepository.findByNameContainingIgnoreCase(name);

        Long countryId;

        try {
            if (countryOpt.isPresent()) {

                countryId = countryOpt.get().getId();

                for (Map.Entry<String, InfoRequest> deathsEntry : covidDeathsRequest[0].deaths().entrySet()) {

                    Death death = new Death();
                    death.setDate(deathsEntry.getKey());
                    death.setTotal(deathsEntry.getValue().getTotal());
                    death.setNews(deathsEntry.getValue().getCaseNews());
                    death.setCountryId(countryId);

                    deathRepository.save(death);
                }
            } else {
                Country country = covidDeathsRequest[0].convertCountry(new Country());
                countryRepository.save(country);

                countryId = country.getId();

                for (Map.Entry<String, InfoRequest> deathsEntry : covidDeathsRequest[0].deaths().entrySet()) {

                    Death death = new Death();
                    death.setDate(deathsEntry.getKey());
                    death.setTotal(deathsEntry.getValue().getTotal());
                    death.setNews(deathsEntry.getValue().getCaseNews());
                    death.setCountryId(countryId);

                    deathRepository.save(death);
                }
            }

        } catch (CountryException ce) {
            throw new CountryException("Failure to save deaths from covid19 in from database");
        }
    }

    @Override
    public void deleteCountryByName(String name) {
        Optional<Country> countryOpt = countryRepository.findByNameContainingIgnoreCase(name);

        if (countryOpt.isPresent()){
            countryRepository.deleteById(countryOpt.get().getId());
        } else {
            throw new CountryException("The country was were not delete in from database");
        }
    }

}
