package com.ejercicio.globallogic.services;

import com.ejercicio.globallogic.DTO.*;
import com.ejercicio.globallogic.exception.*;
import com.ejercicio.globallogic.helper.*;
import com.ejercicio.globallogic.model.*;
import com.ejercicio.globallogic.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UsersServices {
    private final UserRepository usersRepository;
    private final CryptoHelper cryptoHelper;
    private final JwtHelper jwtHelper;

    @Transactional
    public Users save(RegisterDto registerDto) {
        log.info("save | registerDto={}", registerDto);
        String password = cryptoHelper.encode(registerDto.getPassword());
        String token = jwtHelper.createToken(registerDto.getEmail());
        return save(Users.from(registerDto, password, token));
    }

    @Transactional
    public Users save(Users users) {
        log.info("save | users={}", users);
        return usersRepository.save(users);
    }

    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void validNotExistByEmail(String email) throws GlobalLogicException {
        Optional<Users> users = findByEmail(email);
        if (users.isPresent()) {
            throw new GlobalLogicException(MessageHelper.MESSAGE_EMAIL_EXISTS);
        }
    }
}
