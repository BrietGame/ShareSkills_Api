package com.shareskills.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "formations")
public class Formation {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "video_preview_url")
    private String videoPreviewUrl;

    @Column(name = "summary")
    private String summary;

    @Column(name = "price")
    private Double price;

    @Column(name = "teacher_id")
    private Long teacherId;

    @ManyToMany
    @JoinTable(
            name = "formation_chapters",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "chapter_id"))
    public List<Chapter> chapters;

    @ManyToMany
    @JoinTable(
            name = "formation_students",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    public List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "formation_comments",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    public List<Comment> comments;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
