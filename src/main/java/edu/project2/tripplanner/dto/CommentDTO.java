package edu.project2.tripplanner.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long userId;

    private Long placeId;

    private String textOfComment;
}
