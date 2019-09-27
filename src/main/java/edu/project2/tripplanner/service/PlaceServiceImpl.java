package edu.project2.tripplanner.service;

import edu.project2.tripplanner.exception.Message;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService, Message {

    private final PlaceRepository placeRepository;

    @Override
    public List<Place> findAllPlace() {

        return placeRepository.findAll();
    }

    @Override
    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {

        return placeRepository.existsById(id);
    }

    @Override
    public Place getById(Long id) {

        return placeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PLACE_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }
}
