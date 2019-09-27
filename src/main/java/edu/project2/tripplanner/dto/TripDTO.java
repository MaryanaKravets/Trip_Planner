package edu.project2.tripplanner.dto;

import edu.project2.tripplanner.model.Trip;
import lombok.Data;

@Data
public class TripDTO {

    private Long userId;

    private Long tripId;

    private Trip trip;
}
