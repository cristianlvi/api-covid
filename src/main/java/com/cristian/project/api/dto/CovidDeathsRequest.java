package com.cristian.project.api.dto;

import com.cristian.project.api.model.Country;

import java.util.Map;

public record CovidDeathsRequest(

        String country,
        String region,
        Map<String, InfoRequest> deaths
) {

    public Country convertCountry(Country country) {
        country.setName(this.country);
        country.setRegion(this.region);

        return country;
    }

}
