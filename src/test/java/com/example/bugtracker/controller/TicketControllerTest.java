package com.example.bugtracker.controller;

import com.example.bugtracker.AbstractMvcInit;
import com.example.bugtracker.dto.ticket.*;
import com.example.bugtracker.enums.SeverityTicket;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TicketControllerTest extends AbstractMvcInit {
    private final String ticketPath = "/tickets";

    @Test
    void getTicketListSortedByAuthor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ticketPath)
                        .header("Authorization", userJWT)
                        .param("author.equals", "6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", Matchers.is(2)));
    }

    @Test
    void createTicketWithoutSeverityField_thenStatus400() throws Exception {
        TicketCreateDto newTicket = TicketCreateDto.builder()
                .description("New bug")
                .build();

        mvc.perform(MockMvcRequestBuilders.post(ticketPath)
                        .header("Authorization", userJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newTicket)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations.[0].message", Matchers.is("Field severity is mandatory")));
    }

    @Test
    void createCorrectTicket_thenCheckStatus() throws Exception {
        TicketCreateDto newTicket = TicketCreateDto.builder()
                .severity(SeverityTicket.LOW)
                .description("New bug")
                .build();

        mvc.perform(MockMvcRequestBuilders.post(ticketPath)
                        .header("Authorization", userJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newTicket)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("NEW")))
                .andExpect(jsonPath("$.reporter", Matchers.is("nick@customer.com")));
    }

    @Test
    void addCommentToTicket_thenCheckCommentListSize() throws Exception {
        CommentCreateDto newComment = new CommentCreateDto("New comment");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(ticketPath + "/1")
                        .header("Authorization", userJWT))
                .andExpect(status().isOk())
                .andReturn();
        TicketDto ticketDto = super.mapFromJson(mvcResult.getResponse().getContentAsString(), TicketDto.class);

        mvc.perform(MockMvcRequestBuilders.post(ticketPath + "/1/comment")
                        .header("Authorization", userJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newComment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments", hasSize(ticketDto.getComments().size() + 1)));
    }

    @Test
    void assignTicketToNotDeveloper_thenStatus403() throws Exception {
        TicketAssignDto ticketAssignDto = TicketAssignDto.builder()
                .responsible("bob_admin@rd.com")
                .message("Ticket assignment")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/1/assign")
                        .header("Authorization", adminJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(ticketAssignDto)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", Matchers.is("User bob_admin@rd.com hasn't correct role for assignment")));
    }

    @Test
    void assignTicketInNonNewStatus_thenStatus403() throws Exception {
        TicketAssignDto ticketAssignDto = TicketAssignDto.builder()
                .responsible("john_dev@rd.com")
                .message("Ticket assignment")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/2/assign")
                        .header("Authorization", adminJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(ticketAssignDto)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", Matchers.is("Current ticket status ASSIGNED isn't acceptable for operation")));
    }

    @Test
    void assignTicketCorrect() throws Exception {
        TicketAssignDto ticketAssignDto = TicketAssignDto.builder()
                .responsible("john_dev@rd.com")
                .message("Ticket assignment")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/1/assign")
                        .header("Authorization", adminJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(ticketAssignDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responsible", Matchers.is("john_dev@rd.com")))
                .andExpect(jsonPath("$.status", Matchers.is("ASSIGNED")));
    }

    @Test
    void solveTicketCorrect() throws Exception {
        CommentCreateDto newComment = new CommentCreateDto("New comment");

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/2/solve")
                        .header("Authorization", devJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newComment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("SOLVED")));
    }

    @Test
    void verifyTicketAndMarkAsUnresolved() throws Exception {
        TicketVerifyDto ticketVerifyDto = TicketVerifyDto.builder()
                .isCorrect(false)
                .message("Ticket verify false")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/3/verify")
                        .header("Authorization", qaJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(ticketVerifyDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("ASSIGNED")));
    }

    @Test
    void verifyTicketAndMarkAsClosed() throws Exception {
        TicketVerifyDto ticketVerifyDto = TicketVerifyDto.builder()
                .isCorrect(true)
                .message("Ticket verify true")
                .build();

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/4/verify")
                        .header("Authorization", qaJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(ticketVerifyDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("CLOSED")));
    }

    @Test
    void reopenTicketCorrect() throws Exception {
        CommentCreateDto newComment = new CommentCreateDto("Reopen ticket");

        mvc.perform(MockMvcRequestBuilders.patch(ticketPath + "/5/reopen")
                        .header("Authorization", userJWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newComment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("REOPEN")));
    }

    @Test
    void deleteTicket() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ticketPath + "/6")
                        .header("Authorization", adminJWT))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.delete(ticketPath + "/6")
                        .header("Authorization", adminJWT))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(ticketPath + "/6")
                        .header("Authorization", adminJWT))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}