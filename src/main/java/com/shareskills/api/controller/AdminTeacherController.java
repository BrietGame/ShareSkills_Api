package com.shareskills.api.controller;

import com.shareskills.api.model.Teacher;
import com.shareskills.api.model.dto.TeacherDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/teacher")
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public ResponseEntity<ResponseJson<List<Teacher>>> getTeachers() {
        return ResponseEntity.ok(new ResponseJson<>(teacherService.findAll(), HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJson<Teacher>> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        return teacher.map(value -> ResponseEntity.ok(new ResponseJson<>(value, HttpStatus.OK.value()))).orElseGet(() -> ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value())));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseJson<Teacher>> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(new ResponseJson<>(teacherService.createTeacher(teacherDTO), HttpStatus.CREATED.value()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJson<Teacher>> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            teacherDTO.setId(id);
            return ResponseEntity.ok(new ResponseJson<>(teacherService.updateTeacher(teacherDTO), HttpStatus.OK.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseJson<Teacher>> deleteTeacher(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            teacherService.deleteTeacher(teacher.get());
            return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NO_CONTENT.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }
}
