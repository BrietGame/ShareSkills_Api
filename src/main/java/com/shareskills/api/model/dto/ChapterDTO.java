package com.shareskills.api.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChapterDTO {
    private Long id;
    private String name;
    private String videoUrl;
    private Integer duration;
}
