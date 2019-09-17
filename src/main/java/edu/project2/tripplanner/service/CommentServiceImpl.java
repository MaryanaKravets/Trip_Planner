package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.CommentDTO;
import edu.project2.tripplanner.dto.CommentIdDTO;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.Comment;
import edu.project2.tripplanner.repository.CommentRepository;
import edu.project2.tripplanner.repository.PlaceRepository;
import edu.project2.tripplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final  UserRepository userRepository;
    private final PlaceRepository placeRepository;

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
    public void deleteCommentById(CommentIdDTO commentIdDTO) {
         commentRepository.findByIdAndUserIdAndPlaceId(commentIdDTO.getCommentId(), commentIdDTO.getUserId(), commentIdDTO.getPlaceId()).map(c -> {
            commentRepository.deleteById(commentIdDTO.getCommentId());

            return ResponseEntity.ok().build();
        })
                .orElseThrow(()->new NotFoundException(String
                        .format("Comment with id '%s' not found, or user with id '%s' not found, or place with id '%s' not found"
                                ,commentIdDTO.getCommentId(),commentIdDTO.getUserId(),commentIdDTO.getPlaceId())));
    }

    @Override
    public void addComment(CommentDTO commentDTO){
        userRepository.findById(commentDTO.getUserId()).map(u -> {
            commentDTO.getComment().setUser(u);

            return commentRepository.save(commentDTO.getComment());
        })
                .orElseThrow(()->new NotFoundException(String.format("User with id '%s' not found",commentDTO.getUserId())));
        commentRepository.save(commentDTO.getComment());
    }

    @Override
    public Comment editComment(CommentDTO commentDTO, Long commentId) {
        if (!userRepository.existsById(commentDTO.getUserId()) | !placeRepository.existsById(commentDTO.getPlaceId())) {
            throw new NotFoundException(String
                    .format("User with id '%s' not found, or place with id '%s' not found",commentDTO.getUserId(),commentDTO.getPlaceId()));
        }
        return commentRepository.findByIdAndUserIdAndPlaceId(commentId, commentDTO.getUserId(), commentDTO.getPlaceId()).map(c -> {
            c.setTextOfComment(commentDTO.getComment().getTextOfComment());
            return commentRepository.save(commentDTO.getComment());
        }).orElseThrow(()->new NotFoundException(String
                .format("Comment with id '%s' not found, or user with id '%s' not found, or place with id '%s' not found"
                        ,commentId,commentDTO.getUserId(),commentDTO.getPlaceId())));
    }

    @Override
    public List<Comment> findAll() {

        return commentRepository.findAll();
    }
}
