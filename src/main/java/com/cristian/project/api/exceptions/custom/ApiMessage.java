package com.cristian.project.api.exceptions.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ApiMessage {

    private int errorCode;
    private String message;
    private LocalDate localDate;

}
