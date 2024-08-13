package com.stockManagment.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude()
public class ExceptionDto {
    private Integer errorCode;
    private String businessDescribtion;
    private String errorDescription;
    private Set<String> validationErrors;
    private Map<String,String> errors;
}
