package com.shareskills.api.service;

import com.shareskills.api.mapper.ChapterMapper;
import com.shareskills.api.mapper.FormationMapper;
import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.Formation;
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

    @Autowired
    private FormationService formationService;

    @Autowired
    private FormationMapper formationMapper;

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
        Chapter newChapter = chapterMapper.toEntity(chapterDTO);
        Optional<Formation> optionalFormation = formationService.findById(chapterDTO.getFormationId());
        if (optionalFormation.isEmpty()) {
            throw new IllegalArgumentException("Formation not found");
        }
        Chapter chapter = chapterRepository.save(newChapter);
        formationService.addChapterOnFormation(newChapter, optionalFormation.get());
        return chapter;
    }

    public Chapter updateChapter(ChapterDTO chapterDTO) {
        return chapterRepository.save(chapterMapper.toEntity(chapterDTO));
    }

    public void deleteChapter(Chapter chapter) {
        chapterRepository.delete(chapter);
    }
}
