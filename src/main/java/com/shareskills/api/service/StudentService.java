package com.shareskills.api.service;

import com.shareskills.api.mapper.StudentMapper;
import com.shareskills.api.model.Student;
import com.shareskills.api.model.dto.StudentDTO;
import com.shareskills.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student createStudent(StudentDTO studentDTO) {
        return studentRepository.save(studentMapper.toEntity(studentDTO));
    }

    public Student updateStudent(StudentDTO studentDTO) {
        return studentRepository.save(studentMapper.toEntity(studentDTO));
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
