package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Optional<Place> findPlaceById(Long id);

    List<Place> findAllPlace();

    void deletePlaceById(Long id);
}
