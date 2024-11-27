package com.shareskills.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormationDTO {
    private Long id;

    @NotBlank(message = "Le nom de la formation est requis")
    private String name;
    private String description;

    @NotBlank(message = "La vidéo de présentation est requise")
    private String videoPreviewUrl;
    private String summary;

    @NotNull(message = "Le prix de la formation est requis")
    private Double price;
}
