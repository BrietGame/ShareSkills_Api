package com.shareskills.api.repository;

import com.shareskills.api.model.Formation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormationRepository extends CrudRepository<Formation, Long> {
    List<Formation> findByTeacherId(Long teacherId);
}
