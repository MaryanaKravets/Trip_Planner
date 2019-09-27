package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.CommentDTO;
import edu.project2.tripplanner.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findByUserId(Long userId);

    Comment getByIdAndUserId(Long id, Long userId);

    Optional<Comment> findByIdAndUserIdAndPlaceId(Long id, Long userId, Long placeId);

    void deleteCommentById(Long commentId);

    void addComment(CommentDTO commentDTO);

    Comment editComment(CommentDTO commentDTO, Long commentId);

    List<Comment> findAll();
}
