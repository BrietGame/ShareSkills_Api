package com.shareskills.api.repository;

import com.shareskills.api.model.Chapter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChapterRepository extends CrudRepository<Chapter, Long> {
    List<Chapter> findByFormationId(Long formationId);
}
