package com.example.bugtracker.utils;

import com.example.bugtracker.enums.StatusTicket;
import com.example.bugtracker.exception.IncorrectFlowException;
import com.example.bugtracker.model.Role;
import com.example.bugtracker.model.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ticket processing util class
 */

public class TicketUtil {
    private static final String FORBIDDEN_ASSIGNMENT = "User %s hasn't correct role for assignment";
    private static final String INCORRECT_TICKET_STATUS = "Current ticket status %s isn't acceptable for operation";

    /**
     * Checking including user's role into set of role
     *
     * @param user user
     * @param correctRoles set of role
     */
    public static void checkCorrectRole(User user, Set<String> correctRoles) {
        Set<String> userRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        userRoles.retainAll(correctRoles);
        if (userRoles.size() == 0) {
            throw new IncorrectFlowException(String.format(FORBIDDEN_ASSIGNMENT, user.getEmail()));
        }
    }

    /**
     * Checking including ticket's status into set of status
     *
     * @param srcStatus ticket's status
     * @param dstStatuses set of status
     */
    public static void checkCorrectTicketStatus(StatusTicket srcStatus, Set<StatusTicket> dstStatuses) {
        if (!dstStatuses.contains(srcStatus)) {
            throw new IncorrectFlowException(String.format(INCORRECT_TICKET_STATUS, srcStatus.name()));
        }
    }

}
