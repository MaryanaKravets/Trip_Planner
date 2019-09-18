package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.CommentDTO;
import edu.project2.tripplanner.dto.CommentIdDTO;
import edu.project2.tripplanner.exception.Message;
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
public class CommentServiceImpl implements CommentService, Message {
    private final CommentRepository commentRepository;
    private final  UserRepository userRepository;
    private final PlaceRepository placeRepository;

    private Long userId;
    private Long placeId;

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
         userId=commentIdDTO.getUserId();
        Long commentId=commentIdDTO.getCommentId();
         placeId=commentIdDTO.getPlaceId();
         commentRepository.findByIdAndUserIdAndPlaceId(commentId, userId, placeId).map(c -> {
            commentRepository.deleteById(commentId);

            return ResponseEntity.ok().build();
        })                .orElseThrow(()->new NotFoundException(String.format(mes2,commentId,userId,placeId)));
    }

    @Override
    public void addComment(CommentDTO commentDTO){
         userId=commentDTO.getUserId();
        userRepository.findById(userId).map(u -> {
            commentDTO.getComment().setUser(u);

            return commentRepository.save(commentDTO.getComment());
        })
                .orElseThrow(()->new NotFoundException(String.format(mes1,userId)));
    }

    @Override
    public Comment editComment(CommentDTO commentDTO, Long commentId) {
         userId=commentDTO.getUserId();
         placeId=commentDTO.getPlaceId();
        if (!userRepository.existsById(userId) || !placeRepository.existsById(placeId)) {
            throw new NotFoundException(String
                    .format(mes3,userId,placeId));
        }
        return commentRepository.findByIdAndUserIdAndPlaceId(commentId,userId, placeId).map(c -> {
            c.setTextOfComment(commentDTO.getComment().getTextOfComment());
            return commentRepository.save(commentDTO.getComment());
        }).orElseThrow(()->new NotFoundException(String.format(mes2,commentId,userId,placeId)));
    }

    @Override
    public List<Comment> findAll() {

        return commentRepository.findAll();
    }
}
