package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.Place;

import java.util.List;

public interface PlaceService {

    List<Place> findAllPlace();

    void deletePlaceById(Long id);

    boolean existsById(Long id);

    Place getById(Long id);

    void save(Place place);
}
