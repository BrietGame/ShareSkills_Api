package com.shareskills.api.service;

import com.shareskills.api.mapper.TeacherMapper;
import com.shareskills.api.model.Teacher;
import com.shareskills.api.model.dto.TeacherDTO;
import com.shareskills.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    public Teacher createTeacher(TeacherDTO teacherDTO) {
        return teacherRepository.save(teacherMapper.toEntity(teacherDTO));
    }

    public Teacher updateTeacher(TeacherDTO teacherDTO) {
        return teacherRepository.save(teacherMapper.toEntity(teacherDTO));
    }

    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
