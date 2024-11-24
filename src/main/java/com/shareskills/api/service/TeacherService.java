package com.shareskills.api.service;

import com.shareskills.api.model.Teacher;
import com.shareskills.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepository.findAll();
    }
}
