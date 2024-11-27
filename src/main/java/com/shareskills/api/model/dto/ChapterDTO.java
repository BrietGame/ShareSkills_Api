package com.shareskills.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChapterDTO {
    private Long id;

    @NotBlank(message = "Le nom du chapitre est requis")
    private String name;

    @NotBlank(message = "La vidéo du chapitre est requise")
    private String videoUrl;

    @NotBlank(message = "La durée du chapitre est requise")
    private Integer duration;
}
