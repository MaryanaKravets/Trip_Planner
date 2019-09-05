package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comment> findByUserId(Long userId);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);

    Optional<Comment> findByIdAndUserIdAndPlaceId(Long id, Long userId, Long placeId);

    ResponseEntity<?> deleteCommentById(Long id, Long userId, Long placeId);

    ResponseEntity<Comment> addComment(Long userId, Long placeId, Comment comment);

    Comment editComment(Long userId, Long placeId,Long commentId, Comment comment);

    List<Comment> findAll();
}
