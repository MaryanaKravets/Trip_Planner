package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.Comment;
import edu.project2.tripplanner.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CommentsController {

    private ICommentService iCommentService;

    @ResponseBody
    @GetMapping("/comments")
    public List<Comment> findAllComments() {

        return iCommentService.findAll();
    }
    @ResponseBody
    @GetMapping("/user/{userId}/comments")
    public List<Comment> getAllCommentsByUserId(@PathVariable(value = "userId") Long userId) {

        return iCommentService.findByUserId(userId);
    }

    @ResponseBody
    @GetMapping("/user/{userId}/comments/{commentId}")
    public Optional<Comment> findCommentByIdAndUserId(@PathVariable(value = "userId") Long userId,
                                                     @PathVariable(value = "commentId") Long commentId) {

        return iCommentService.findByIdAndUserId(commentId,userId);
    }


    @ResponseBody
    @PostMapping("/user/{userId}/place/{placeId}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable(value = "userId") Long userId,
                                               @PathVariable(value = "placeId") Long placeId,
                                               @RequestBody Comment comment) {
        iCommentService.addComment(userId,placeId,comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


    @ResponseBody
    @DeleteMapping("/user/{userId}/place/{placeId}/comments/{commentId}")
    public void deleteComment(@PathVariable("userId") Long userId,
                              @PathVariable("placeId") Long placeId,
                              @PathVariable("commentId") Long commentId){
        iCommentService.deleteCommentById(userId, placeId, commentId);
    }


    @ResponseBody
    @PutMapping("/user/{userId}/place/{placeId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(name = "userId") Long userId,
                           @PathVariable(name = "placeId") Long placeId,
                           @PathVariable(name = "commentId") Long commentId,
                                                 @RequestBody Comment comment) {
       return iCommentService.editComment(userId, placeId, commentId, comment);
    }
}
