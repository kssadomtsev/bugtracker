package com.example.bugtracker.controller;

import com.example.bugtracker.AbstractMvcInit;
import com.example.bugtracker.dto.IdNameDTO;
import com.example.bugtracker.enums.SearchDataType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchControllerTest extends AbstractMvcInit {
    private final String searchPath = "/search/list";

    @Test
    void searchAllRoles() throws Exception {
         mvc.perform(MockMvcRequestBuilders.get("/" + SearchDataType.ROLES.name() + searchPath)
                        .header("Authorization", userJWT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name").value(Matchers.containsInAnyOrder("ADMIN","USER","DEVELOPER","QA")));
    }
}