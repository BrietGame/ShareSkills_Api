package com.shareskills.api.controller;

import com.shareskills.api.model.Comment;
import com.shareskills.api.model.dto.CommentDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public ResponseEntity<ResponseJson<List<Comment>>> getComments() {
        return ResponseEntity.ok(new ResponseJson<>(commentService.findAll(), HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJson<Comment>> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.map(value -> ResponseEntity.ok(new ResponseJson<>(value, HttpStatus.OK.value()))).orElseGet(() -> ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value())));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseJson<Comment>> createComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(new ResponseJson<>(commentService.createComment(commentDTO), HttpStatus.CREATED.value()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJson<Comment>> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            commentDTO.setId(id);
            return ResponseEntity.ok(new ResponseJson<>(commentService.updateComment(commentDTO), HttpStatus.OK.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseJson<Comment>> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.deleteComment(comment.get());
            return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NO_CONTENT.value()));
        }
        return ResponseEntity.ok(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }
}
