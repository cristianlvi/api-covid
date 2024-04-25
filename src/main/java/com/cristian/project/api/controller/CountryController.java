package com.cristian.project.api.controller;

import com.cristian.project.api.dto.CovidCasesRequest;
import com.cristian.project.api.exceptions.custom.GenericException;
import com.cristian.project.api.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/api/covid19")
public class CountryController {


    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/search")
    public ResponseEntity<CovidCasesRequest> searchCountry(@RequestParam("country") String name) throws GenericException {
        CovidCasesRequest covidCases = countryService.SearchNameCountry(name);


        return ResponseEntity.status(200).body(covidCases);
    }


    @GetMapping("/save/cases")
    public ResponseEntity<String> saveCountryCases(@RequestParam("country") String name) {
        countryService.saveCountryCases(name);


        return ResponseEntity.status(HttpStatus.CREATED).body("save country and cases from covid19 in database");
    }

    @GetMapping("/save/deaths")
    public ResponseEntity<String> saveCountryTypeDeaths(@RequestParam("country") String name) {
        countryService.saveCountryDeaths(name);


        return ResponseEntity.status(HttpStatus.CREATED).body("save deaths in the from country");
    }


    @DeleteMapping("delete/{country}")
    public ResponseEntity<String> deleteCountry(@PathVariable("country") String country) {
        countryService.deleteCountryByName(country);

        return ResponseEntity.ok().body("country was were delete");
    }

}
