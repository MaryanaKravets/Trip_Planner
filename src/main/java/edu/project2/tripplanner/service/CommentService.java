package edu.project2.tripplanner.service;

import edu.project2.tripplanner.exception.NotFoundExceptions;
import edu.project2.tripplanner.model.Comment;
import edu.project2.tripplanner.repository.CommentRepository;
import edu.project2.tripplanner.repository.PlaceRepository;
import edu.project2.tripplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PlaceRepository placeRepository;

    @Override
    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public Optional<Comment> findByIdAndUserId(Long id, Long userId) {
        return commentRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Optional<Comment> findByIdAndUserIdAndPlaceId(Long id, Long userId, Long placeId) {
        return commentRepository.findByIdAndUserIdAndPlaceId(id, userId, placeId);
    }

    @Override
    public void deleteCommentById(Long id, Long userId, Long placeId) {
         commentRepository.findByIdAndUserIdAndPlaceId(id, userId, placeId).map(c -> {
            commentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        })
                .orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public void addComment(Long userId, Long placeId, Comment comment) {
        userRepository.findById(userId).map(u -> {
            comment.setUser(u);
            return commentRepository.save(comment);
        })
                .orElseThrow(NotFoundExceptions::new);
        commentRepository.save(comment);
    }

    @Override
    public Comment editComment(Long userId, Long placeId, Long commentId, Comment comment) {
        if (!userRepository.existsById(userId) | !placeRepository.existsById(placeId)) {
            throw new NotFoundExceptions();
        }
        return commentRepository.findByIdAndUserIdAndPlaceId(commentId, userId, placeId).map(c -> {
            c.setTextOfComment(comment.getTextOfComment());
            return commentRepository.save(comment);
        }).orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
