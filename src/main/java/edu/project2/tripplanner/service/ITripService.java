package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Trip;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ITripService {

    void deleteTripById(Long id);

    Trip deletePlaceFromTrip(Long userId, Long tripId, Long placeId);

    ResponseEntity<Trip> addTrip(Long userId, Trip trip);

    Trip editTrip(Long userId, Long tripId, Trip trip);

    List<Trip> findAllByUserId(Long userId);

    Trip addPlaceToTrip(Long userId, Long tripId, Long placeId);

    Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId);


}
