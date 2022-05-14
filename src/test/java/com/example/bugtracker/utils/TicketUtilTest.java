package com.example.bugtracker.utils;

import com.example.bugtracker.enums.StatusTicket;
import com.example.bugtracker.exception.DuplicateEntityException;
import com.example.bugtracker.exception.IncorrectFlowException;
import com.example.bugtracker.model.Role;
import com.example.bugtracker.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TicketUtilTest {
    @Test
    void checkIncorrectRoleCombination_thenThrowException() {
        Role adminRole = Role.builder()
                .name("ADMIN")
                .build();
        String userRole = "USER";
        User user = User.builder()
                .roles(Set.of(adminRole))
                .build();

        Assertions.assertThatThrownBy(() -> TicketUtil.checkCorrectRole(user, Set.of(userRole)))
                .isInstanceOf(IncorrectFlowException.class);
    }

    @Test
    void checkIncorrectCorrectTicketStatus_thenThrowException() {
        StatusTicket srcStatus = StatusTicket.NEW;
        Set<StatusTicket> dstStatuses = Set.of(StatusTicket.CLOSED, StatusTicket.ASSIGNED);

        Assertions.assertThatThrownBy(() -> TicketUtil.checkCorrectTicketStatus(srcStatus, dstStatuses))
                .isInstanceOf(IncorrectFlowException.class);
    }
}