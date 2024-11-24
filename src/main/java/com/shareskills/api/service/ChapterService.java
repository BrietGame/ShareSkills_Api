package com.shareskills.api.service;

import com.shareskills.api.mapper.ChapterMapper;
import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.dto.ChapterDTO;
import com.shareskills.api.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterMapper chapterMapper;

    public Optional<Chapter> findById(Long id) {
        return chapterRepository.findById(id);
    }

    public List<Chapter> findByFormationId(Long formationId) {
        return chapterRepository.findByFormationId(formationId);
    }

    public List<Chapter> findAll() {
        return (List<Chapter>) chapterRepository.findAll();
    }

    public Chapter createChapter(ChapterDTO chapterDTO) {
        return chapterRepository.save(chapterMapper.toEntity(chapterDTO));
    }

    public Chapter updateChapter(ChapterDTO chapterDTO) {
        return chapterRepository.save(chapterMapper.toEntity(chapterDTO));
    }

    public void deleteChapter(Chapter chapter) {
        chapterRepository.delete(chapter);
    }
}
