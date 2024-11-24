package com.shareskills.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "formation_id")
    private Long formationId;

    @ManyToMany
    @JoinTable(
            name = "chapter_quizzes",
            joinColumns = @JoinColumn(name = "chapter_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    public List<Quiz> quizzes;

    @ManyToMany
    @JoinTable(
            name = "chapter_comments",
            joinColumns = @JoinColumn(name = "chapter_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    public List<Comment> comments;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt = ZonedDateTime.now();
}
