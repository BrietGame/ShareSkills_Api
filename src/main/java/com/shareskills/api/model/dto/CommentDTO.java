package com.shareskills.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long formationId;

    @NotBlank(message = "La note du commentaire est requise")
    private Integer note;

    @NotBlank(message = "Le contenu du commentaire est requis")
    private String content;
}
