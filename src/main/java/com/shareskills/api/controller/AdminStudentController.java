package com.shareskills.api.controller;

import com.shareskills.api.model.Student;
import com.shareskills.api.model.dto.StudentDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/student")
public class AdminStudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseJson<List<Student>> getStudents() {
        return new ResponseJson<>(studentService.findAll(), HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ResponseJson<Optional<Student>> getStudentById(Long id) {
        return new ResponseJson<>(studentService.findById(id), HttpStatus.OK.value());
    }

    @PostMapping("/create")
    public ResponseJson<Student> createStudent(StudentDTO studentDTO) {
        return new ResponseJson<>(studentService.createStudent(studentDTO), HttpStatus.CREATED.value());
    }

    @PostMapping("/update/{id}")
    public ResponseJson<Student> updateStudent(Long id, StudentDTO studentDTO) {
        Optional<Student> studentOptional = studentService.findById(id);
        if (studentOptional.isPresent()) {
            studentDTO.setId(id);
            return new ResponseJson<>(studentService.updateStudent(studentDTO), HttpStatus.OK.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

    @PostMapping("/delete/{id}")
    public ResponseJson<Student> deleteStudent(Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(student.get());
            return new ResponseJson<>(null, HttpStatus.NO_CONTENT.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }
}
