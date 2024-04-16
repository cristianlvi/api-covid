package com.cristian.project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InfoRequest {

    private long total;
    @JsonProperty(value = "new")
    private long caseNews;

}
