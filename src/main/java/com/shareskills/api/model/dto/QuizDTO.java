package com.shareskills.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDTO {
    private Long id;

    @NotBlank(message = "La question du quiz est requise")
    private String question;

    @NotBlank(message = "L'option 1 du quiz est requise")
    private String option1;

    @NotBlank(message = "L'option 2 du quiz est requise")
    private String option2;
    private String option3;
    private String option4;

    @NotBlank(message = "La réponse du quiz est requise")
    private String answer;
}
