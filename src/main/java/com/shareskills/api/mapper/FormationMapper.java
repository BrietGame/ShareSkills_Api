package com.shareskills.api.mapper;

import com.shareskills.api.model.Formation;
import com.shareskills.api.model.dto.FormationDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
}
