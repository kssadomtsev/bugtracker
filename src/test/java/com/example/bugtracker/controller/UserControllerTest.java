package com.example.bugtracker.controller;

import com.example.bugtracker.AbstractMvcInit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractMvcInit {
    private final String userPath = "/users";

    @Test
    void accessWithoutAdminRole_thenStatus403() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(userPath )
                .header("Authorization", userJWT))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void getFilteredUserList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(userPath )
                        .header("Authorization", adminJWT)
                        .param("company.equals", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", Matchers.is(2)));
    }
}