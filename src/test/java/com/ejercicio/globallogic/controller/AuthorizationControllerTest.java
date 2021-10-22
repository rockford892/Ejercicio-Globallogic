package com.ejercicio.globallogic.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.ejercicio.globallogic.DTO.*;
import com.ejercicio.globallogic.helper.*;

@Slf4j
@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorizationControllerTest extends TestHelper{
    
    private static String urlTemplate = "/authorization";

    @Autowired
    private DataHelper dataHelper;

    @Test
    @Transactional
    public void registerTestErrorEmailDuplicate() throws Exception {
        registerTest();
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(dataHelper.getRegisterDefault())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }

    @Test
    @Transactional
    public void registerTest() throws Exception {
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(dataHelper.getRegisterDefault())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.modified").isNotEmpty())
                .andExpect(jsonPath("$.last_login").isNotEmpty())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.isactive").isBoolean());
    }

    @Test
    @Transactional
    public void registerTestErrorEmailNull() throws Exception {
        RegisterDto registerDto = dataHelper.getRegisterDefault();
        registerDto.setEmail(null);
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(registerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[email] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void registerTestErrorEmailRegularExpression() throws Exception {
        RegisterDto registerDto = dataHelper.getRegisterDefault();
        registerDto.setEmail("jhon.doe@");
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(registerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[email] mal formado"));
    }

    @Test
    @Transactional
    public void registerTestErrorNameNull() throws Exception {
        RegisterDto registerDto = dataHelper.getRegisterDefault();
        registerDto.setName(null);
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(registerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[name] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void registerTestErrorPasswordNull() throws Exception {
        RegisterDto registerDto = dataHelper.getRegisterDefault();
        registerDto.setPassword(null);
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(registerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[password] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void registerTestErrorPasswordRegex() throws Exception {
        RegisterDto registerDto = dataHelper.getRegisterDefault();
        registerDto.setPassword("aB1n");
        mockMvc.perform(post(urlTemplate + "/register")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(registerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[password] mal formado"));
    }
}
