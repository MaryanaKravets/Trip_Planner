package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.dto.CommentDTO;
import edu.project2.tripplanner.model.Comment;
import edu.project2.tripplanner.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/all")
    public List<Comment> findAllComments() {

        return commentService.findAll();
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public List<Comment> getAllCommentsByUserId(@PathVariable(value = "userId") Long userId) {

        return commentService.findByUserId(userId);
    }

    @ResponseBody
    @GetMapping("/{commentId}")
    public Comment getCommentByIdAndUserId(@PathVariable(value = "commentId") Long commentId,
                                           @RequestParam(value = "userId") Long userId) {

        return commentService.getByIdAndUserId(commentId, userId);
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @ResponseBody
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteCommentById(commentId);

        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PutMapping("/{commentId}")
    public Comment updateComment(@RequestBody CommentDTO commentDTO,
                                 @PathVariable(name = "commentId") Long commentId) {

        return commentService.editComment(commentDTO, commentId);
    }
}
