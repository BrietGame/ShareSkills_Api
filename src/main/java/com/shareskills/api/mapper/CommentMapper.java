package com.shareskills.api.mapper;

import com.shareskills.api.model.Comment;
import com.shareskills.api.model.dto.CommentDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class CommentMapper implements Mapper<Comment, CommentDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public Class<CommentDTO> getDTOClass() {
        return CommentDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Comment.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Comment.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
