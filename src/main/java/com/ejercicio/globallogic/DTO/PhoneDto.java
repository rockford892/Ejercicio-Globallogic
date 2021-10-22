package com.ejercicio.globallogic.DTO;

import com.ejercicio.globallogic.helper.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDto {
    
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String number;

    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String citycode;

    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String contrycode;
    
}
