package com.shareskills.api.service;

import com.shareskills.api.model.Chapter;
import com.shareskills.api.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Optional<Chapter> findById(Long id) {
        return chapterRepository.findById(id);
    }

    public List<Chapter> findByFormationId(Long formationId) {
        return chapterRepository.findByFormationId(formationId);
    }

    public List<Chapter> findAll() {
        return (List<Chapter>) chapterRepository.findAll();
    }
}
