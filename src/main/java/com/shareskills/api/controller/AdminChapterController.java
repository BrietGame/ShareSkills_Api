package com.shareskills.api.controller;

import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.dto.ChapterDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/chapter")
public class AdminChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/list")
    public ResponseEntity<ResponseJson<List<Chapter>>> getChapters() {
        return ResponseEntity.ok(new ResponseJson<>(chapterService.findAll(), HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJson<Chapter>> getChapterById(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        return chapter.map(value -> ResponseEntity.ok(new ResponseJson<>(value, HttpStatus.OK.value()))).orElseGet(() -> ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value())));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseJson<Chapter>> createChapter(@RequestBody ChapterDTO chapterDTO) {
        return ResponseEntity.ok(new ResponseJson<>(chapterService.createChapter(chapterDTO), HttpStatus.CREATED.value()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJson<Chapter>> updateChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (chapter.isPresent()) {
            chapterDTO.setId(id);
            return ResponseEntity.ok(new ResponseJson<>(chapterService.updateChapter(chapterDTO), HttpStatus.OK.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseJson<Chapter>> deleteChapter(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        if (chapter.isPresent()) {
            chapterService.deleteChapter(chapter.get());
            return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NO_CONTENT.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }
}
