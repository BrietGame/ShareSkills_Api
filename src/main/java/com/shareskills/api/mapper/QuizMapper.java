package com.shareskills.api.mapper;

import com.shareskills.api.model.Quiz;
import com.shareskills.api.model.dto.QuizDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class QuizMapper implements Mapper<Quiz, QuizDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Quiz> getEntityClass() {
        return Quiz.class;
    }

    @Override
    public Class<QuizDTO> getDTOClass() {
        return QuizDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Quiz.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Quiz.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
