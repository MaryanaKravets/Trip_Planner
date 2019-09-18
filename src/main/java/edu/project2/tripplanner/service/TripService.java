package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.TripDTO;
import edu.project2.tripplanner.dto.TripIdDTO;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Trip;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TripService {

    void deleteTripById(Long id);

    Trip deletePlaceFromTrip(TripIdDTO tripIdDTO);

    Trip addTrip(Long userId, Trip trip);

    Trip editTrip(TripDTO tripDTO);

    List<Trip> findAllByUserId(Long userId);

    Trip addPlaceToTrip(TripIdDTO tripIdDTO);

    Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId);


}
