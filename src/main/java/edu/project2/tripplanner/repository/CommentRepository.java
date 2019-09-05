package edu.project2.tripplanner.repository;

import edu.project2.tripplanner.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserId(Long userId);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);

    Optional<Comment> findByIdAndUserIdAndPlaceId(Long id, Long userId, Long placeId);
}
