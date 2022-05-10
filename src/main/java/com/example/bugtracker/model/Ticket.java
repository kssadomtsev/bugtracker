package com.example.bugtracker.model;

import com.example.bugtracker.enums.SeverityTicket;
import com.example.bugtracker.enums.StatusTicket;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeverityTicket severity;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "solved_at")
    private LocalDateTime solvedAt;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    @CreatedBy
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User responsible;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTicket status;

    @Column(nullable = false, length = 4000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = CascadeType.ALL)
    @OrderBy(value = "createdAt asc")
    private List<Comment> comments = new ArrayList<>();
}
