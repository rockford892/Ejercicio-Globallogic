package com.ejercicio.globallogic.helper;

import org.springframework.stereotype.Component;
import com.ejercicio.globallogic.DTO.*;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DataHelper {
    public RegisterDto getRegisterDefaultEmpty() {
        return  new RegisterDto();
    }

    public RegisterDto getRegisterDefault() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Manuel Hernandez");
        registerDto.setEmail("ManuHer.EJ@example.cl");
        registerDto.setPassword("ManHe287");
        registerDto.setPhoneDtos(getPhonesDefault());
        return registerDto;
    }

    public Collection<PhoneDto> getPhonesDefault() {
        Collection<PhoneDto> phoneDtos = new ArrayList<>();
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567890");
        phoneDto.setCitycode("1");
        phoneDto.setContrycode("23");
        phoneDtos.add(phoneDto);
        return phoneDtos;
    }
}
