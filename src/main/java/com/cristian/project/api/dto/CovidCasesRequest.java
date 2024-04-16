package com.cristian.project.api.dto;

import com.cristian.project.api.model.Country;
import lombok.Data;

import java.util.Map;

@Data
public class CovidCasesRequest {

    private String country;
    private String region;
    Map<String, InfoRequest> cases;


    public Country convertCountry(Country country) {
        country.setName(this.country);
        country.setRegion(this.getRegion());

        return country;
    }


}
