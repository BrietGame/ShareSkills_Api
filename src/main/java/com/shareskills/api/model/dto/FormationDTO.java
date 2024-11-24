package com.shareskills.api.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormationDTO {
    private Long id;
    private String name;
    private String description;
    private String videoPreviewUrl;
    private String summary;
    private Double price;
}
