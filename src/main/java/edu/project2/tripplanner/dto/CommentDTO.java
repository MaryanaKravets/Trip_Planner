package edu.project2.tripplanner.dto;

import edu.project2.tripplanner.model.Comment;
import lombok.Data;

@Data
public class CommentDTO {

    private Long userId;

    private Long placeId;

    private Comment comment;
}
