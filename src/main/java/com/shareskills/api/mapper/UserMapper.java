package com.shareskills.api.mapper;

import com.shareskills.api.model.User;
import com.shareskills.api.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public class UserMapper implements Mapper<User, UserDTO> {

    private final List<String> IGNORE_FIELDS = List.of("id");

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    public List<String> getAdminColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = User.class.getDeclaredFields();
        for (Field champ : champs) {
            proprieties.add(champ.getName());
        }
        return proprieties;
    }

    @Override
    public List<String> getColumns() {
        List<String> proprieties = new ArrayList<>();
        Field[] champs = User.class.getDeclaredFields();
        for (Field champ : champs) {
            if (!IGNORE_FIELDS.contains(champ.getName())) {
                proprieties.add(champ.getName());
            }
        }
        return proprieties;
    }
}
