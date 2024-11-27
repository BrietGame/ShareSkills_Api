package com.shareskills.api.mapper;

import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.Formation;
import com.shareskills.api.model.dto.FormationDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class FormationMapper implements Mapper<Formation, FormationDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Formation> getEntityClass() {
        return Formation.class;
    }

    @Override
    public Class<FormationDTO> getDTOClass() {
        return FormationDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Formation.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Formation.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }

    /**
     * Convert Formation to FormationDTO, mapping chapters to a list of IDs.
     */
    public FormationDTO toDTO(Formation formation) {
        FormationDTO dto = new FormationDTO();
        dto.setId(formation.getId());
        dto.setName(formation.getName());
        dto.setChapterIds(
                formation.getChapters()
                        .stream()
                        .map(Chapter::getId)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    /**
     * Convert FormationDTO to Formation, mapping chapter IDs to Chapter entities.
     */
    public Formation toEntity(FormationDTO dto, List<Chapter> allChapters) {
        Formation formation = new Formation();
        formation.setId(dto.getId());
        formation.setName(dto.getName());
        formation.setChapters(
                dto.getChapterIds()
                        .stream()
                        .map(id -> allChapters.stream()
                                .filter(chapter -> chapter.getId().equals(id))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Invalid Chapter ID: " + id)))
                        .collect(Collectors.toList())
        );
        return formation;
    }
}
