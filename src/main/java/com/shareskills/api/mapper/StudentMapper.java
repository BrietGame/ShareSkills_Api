package com.shareskills.api.mapper;

import com.shareskills.api.model.Student;
import com.shareskills.api.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class StudentMapper implements Mapper<Student, StudentDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Class<StudentDTO> getDTOClass() {
        return StudentDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Student.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = Student.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
