package com.ejercicio.globallogic.services;

import com.ejercicio.globallogic.DTO.*;
import com.ejercicio.globallogic.model.*;
import com.ejercicio.globallogic.exception.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AuthorizationService {
    private final UsersServices usersService;
    private final PhoneServices phoneService;

    @Transactional
    public Users register(RegisterDto registerDto) throws GlobalLogicException {
        log.info("register | registerDto={}", registerDto);
        usersService.validNotExistByEmail(registerDto.getEmail());
        Users users = usersService.save(registerDto);
        phoneService.saveAll(users, registerDto);
        return users;
    }
}
