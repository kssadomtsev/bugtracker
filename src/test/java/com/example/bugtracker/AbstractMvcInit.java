package com.example.bugtracker;

import com.example.bugtracker.dto.user.UserLoginDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonParseException;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonMappingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractMvcInit extends AbstractDBInit {
    @Autowired
    protected MockMvc mvc;

    protected String userJWT;
    protected String devJWT;
    protected String qaJWT;
    protected String adminJWT;

    @BeforeAll
    public void setUp() throws Exception {
        devJWT = receiveToken("alice_dev@rd.com", "password");
        userJWT = receiveToken("nick@customer.com", "password");
        qaJWT = receiveToken("anna_qa@rd.com", "password");
        adminJWT = receiveToken("bob_admin@rd.com", "password");
    }

    private String receiveToken(String login, String password) throws Exception {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .login(login)
                .password(password)
                .build();
        String inputJson = mapToJson(userLoginDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        return "Bearer " + mvcResult.getResponse().getContentAsString();
    }


    protected String mapToJson(Object obj) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

//        JavaTimeModule javaTimeModule=new JavaTimeModule();
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
//        objectMapper.registerModule(javaTimeModule);
//        objectMapper.registerModule(new JavaTimeModule()
//                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME)));
        return objectMapper.readValue(json, clazz);
    }

}

