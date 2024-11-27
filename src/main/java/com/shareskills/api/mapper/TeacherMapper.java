package com.shareskills.api.mapper;

import com.shareskills.api.model.Teacher;
import com.shareskills.api.model.dto.TeacherDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class TeacherMapper implements Mapper<Teacher, TeacherDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public Class<TeacherDTO> getDTOClass() {
        return TeacherDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Teacher.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Teacher.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
