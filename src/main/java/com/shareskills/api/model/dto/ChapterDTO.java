package com.shareskills.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "L'ordre du chapitre est requis")
    private Integer orderItem;

    @NotNull(message = "La formation du chapitre est requise")
    private Long formationId;

    @NotNull(message = "La durée du chapitre est requise")
    private Integer duration;
}
