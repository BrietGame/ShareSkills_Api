package com.shareskills.api.repository;

import com.shareskills.api.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findByFormationId(Long formationId);
}
