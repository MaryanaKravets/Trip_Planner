package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService implements IPlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Optional<Place> findPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> findAllPlace() {
        return placeRepository.findAll();
    }

    @Override
    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }
}
