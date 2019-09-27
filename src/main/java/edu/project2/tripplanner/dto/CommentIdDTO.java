package edu.project2.tripplanner.dto;

import lombok.Data;

@Data
public class CommentIdDTO {

    private Long userId;

    private Long placeId;

    private Long commentId;
}
