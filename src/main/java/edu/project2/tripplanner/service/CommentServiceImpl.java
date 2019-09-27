package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.CommentDTO;
import edu.project2.tripplanner.exception.Message;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.Comment;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.User;
import edu.project2.tripplanner.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService, Message {
    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final PlaceServiceImpl placeService;

    @Override
    public List<Comment> findByUserId(Long userId) {

        return commentRepository.findByUserId(userId);
    }

    @Override
    public Comment getByIdAndUserId(Long id, Long userId) {

        return commentRepository.getByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException(String.format(COMMENT_USER_NOT_FOUND_EXCEPTION_MESSAGE, id, userId)));
    }

    @Override
    public Optional<Comment> findByIdAndUserIdAndPlaceId(Long id, Long userId, Long placeId) {

        return commentRepository.findByIdAndUserIdAndPlaceId(id, userId, placeId);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        User user = userService.getById(commentDTO.getUserId());
        Place place=placeService.getById(commentDTO.getPlaceId());

        comment.setTextOfComment(commentDTO.getTextOfComment());
        comment.setUser(user);
        comment.setPlace(place);
        commentRepository.save(comment);
    }

    @Override
    public Comment editComment(CommentDTO commentDTO, Long commentId) {
        Long userId = commentDTO.getUserId();
        Long placeId = commentDTO.getPlaceId();

        Comment comment = commentRepository.findByIdAndUserIdAndPlaceId(commentId, userId, placeId)
                .orElseThrow(() -> new NotFoundException(String.format(COMMENT_USER_PLACE_NOT_FOUND_EXCEPTION_MESSAGE, commentId, userId, placeId)));

        comment.setTextOfComment(commentDTO.getTextOfComment());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {

        return commentRepository.findAll();
    }
}
