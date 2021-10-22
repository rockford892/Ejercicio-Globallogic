package com.ejercicio.globallogic.DTO;

import com.ejercicio.globallogic.helper.MessageHelper;
import com.ejercicio.globallogic.helper.RegexHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto {
    
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String name;

    @Email(regexp = RegexHelper.EMAIL,message = MessageHelper.MESSAGE_ERROR_REGEXP)
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String email;

    @Pattern(regexp = RegexHelper.PASS,message = MessageHelper.MESSAGE_ERROR_REGEXP)
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String password;

    @Valid
    @JsonProperty("phones")
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    @Size(min = 1, message = MessageHelper.MESSAGE_EMPTY_COLLECTION)
    private Collection<PhoneDto> phoneDtos;
}
