package com.shareskills.api.mapper;

import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.dto.ChapterDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class ChapterMapper implements Mapper<Chapter, ChapterDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Chapter> getEntityClass() {
        return Chapter.class;
    }

    @Override
    public Class<ChapterDTO> getDTOClass() {
        return ChapterDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Chapter.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Chapter.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
