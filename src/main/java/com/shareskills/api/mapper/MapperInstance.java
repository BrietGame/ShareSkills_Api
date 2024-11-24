package com.shareskills.api.mapper;

import lombok.Getter;
import org.modelmapper.ModelMapper;


public abstract class MapperInstance {
    @Getter
    protected static final ModelMapper modelMapper = new ModelMapper();

    abstract Class<?> getEntityClass();

    abstract Class<?> getDTOClass();
}
