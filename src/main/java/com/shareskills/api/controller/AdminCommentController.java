package com.shareskills.api.controller;

import com.shareskills.api.model.Comment;
import com.shareskills.api.model.dto.CommentDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public ResponseJson<List<Comment>> getComments() {
        return new ResponseJson<>(commentService.findAll(), HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ResponseJson<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.map(value -> new ResponseJson<>(value, HttpStatus.OK.value())).orElseGet(() -> new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping("/create")
    public ResponseJson<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        return new ResponseJson<>(commentService.createComment(commentDTO), HttpStatus.CREATED.value());
    }

    @PutMapping("/update/{id}")
    public ResponseJson<Comment> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()) {
            commentDTO.setId(id);
            return new ResponseJson<>(commentService.updateComment(commentDTO), HttpStatus.OK.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseJson<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.deleteComment(comment.get());
            return new ResponseJson<>(null, HttpStatus.NO_CONTENT.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }
}
