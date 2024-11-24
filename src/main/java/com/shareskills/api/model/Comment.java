package com.shareskills.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "formation_id")
    private Long formationId;

    @Column(name = "note")
    private Integer note;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();
}
