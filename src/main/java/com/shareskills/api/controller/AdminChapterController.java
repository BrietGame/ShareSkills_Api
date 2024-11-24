package com.shareskills.api.controller;

import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.dto.ChapterDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/chapter")
public class AdminChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/list")
    public ResponseJson<List<Chapter>> getChapters() {
        return new ResponseJson<>(chapterService.findAll(), HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ResponseJson<Chapter> getChapterById(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        return chapter.map(value -> new ResponseJson<>(value, HttpStatus.OK.value())).orElseGet(() -> new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping("/create")
    public ResponseJson<Chapter> createChapter(@RequestBody ChapterDTO chapterDTO) {
        return new ResponseJson<>(chapterService.createChapter(chapterDTO), HttpStatus.CREATED.value());
    }

    @PutMapping("/update/{id}")
    public ResponseJson<Chapter> updateChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (chapter.isPresent()) {
            chapterDTO.setId(id);
            return new ResponseJson<>(chapterService.updateChapter(chapterDTO), HttpStatus.OK.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseJson<Chapter> deleteChapter(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (chapter.isPresent()) {
            chapterService.deleteChapter(chapter.get());
            return new ResponseJson<>(null, HttpStatus.NO_CONTENT.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }
}
