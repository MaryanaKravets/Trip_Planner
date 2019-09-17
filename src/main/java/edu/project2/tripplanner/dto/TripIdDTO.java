package edu.project2.tripplanner.dto;


import lombok.Data;

@Data
public class TripIdDTO {

    private Long userId;

    private Long placeId;

    private Long tripId;
}
