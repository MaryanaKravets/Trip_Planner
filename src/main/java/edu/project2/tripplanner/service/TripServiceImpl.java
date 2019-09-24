package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.TripDTO;
import edu.project2.tripplanner.dto.TripIdDTO;
import edu.project2.tripplanner.exception.Message;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Transport;
import edu.project2.tripplanner.model.Trip;
import edu.project2.tripplanner.model.User;
import edu.project2.tripplanner.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService, Message {

    private final TripRepository tripRepository;
    private final PlaceServiceImpl placeService;
    private final UserServiceImpl userService;

    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteTripById(id);
    }

    @Override
    public Trip deletePlaceFromTrip(TripIdDTO tripIdDTO) {
        Long userId = tripIdDTO.getUserId();
        Long placeId = tripIdDTO.getPlaceId();
        Long tripId = tripIdDTO.getTripId();
        Place place = placeService.getById(placeId);

        return tripRepository.findTripByIdAndUserId(userId, tripId)
                .map(t -> {
                    t.removePlace(place);
                    placeService.save(place);
                    return tripRepository.save(t);
                })
                .orElseThrow(() -> new NotFoundException(String.format(TRIP_USER_NOT_FOUND_EXCEPTION_MESSAGE, tripId, userId)));
    }

    @Override
    public void addTrip(Long userId, Trip trip) {
        User user = userService.getById(userId);
        Trip trip1 = createTrip(trip);
        user.getTripList().add(trip1);
        trip1.setUser(user);

        tripRepository.save(trip1);
    }

    @Override
    public Trip editTrip(TripDTO tripDTO) {
        Long userId = tripDTO.getUserId();
        Long tripId = tripDTO.getTripId();
        if (!userService.existsById(userId) || !tripRepository.existsById(tripId)) {
            throw new NotFoundException(String
                    .format(TRIP_USER_NOT_FOUND_EXCEPTION_MESSAGE, tripId, userId));
        }
        return tripRepository.findTripByIdAndUserId(userId, tripId)
                .map(t -> tripRepository.save(createTrip(t)))
                .orElseThrow(() -> new NotFoundException(String.format(TRIP_USER_NOT_FOUND_EXCEPTION_MESSAGE, tripId, userId)));
    }

    @Override
    public List<Trip> findAllByUserId(Long userId) {

        return tripRepository.findAllByUserId(userId);
    }

    @Override
    public Trip addPlaceToTrip(TripIdDTO tripIdDTO) {
        Long userId = tripIdDTO.getUserId();
        Long placeId = tripIdDTO.getPlaceId();
        Long tripId = tripIdDTO.getTripId();
        Place place = placeService.getById(placeId);
        return tripRepository.findTripByIdAndUserId(userId, tripId).map(t -> {
            t.addPlace(place);
            placeService.save(place);

            return tripRepository.save(t);
        })
                .orElseThrow(() -> new NotFoundException(String.format(TRIP_USER_NOT_FOUND_EXCEPTION_MESSAGE, tripId, userId)));
    }

    @Override
    public Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId) {

        return tripRepository.findTripByIdAndUserId(userId, tripId);
    }

    private Trip createTrip(Trip trip) {
        Trip trip1 = new Trip();
        trip1.setDepartureDay(trip.getDepartureDay());
        trip1.setDayOfArrival(trip.getDayOfArrival());
        Random random = new Random();
        int i = random.nextInt(Transport.values().length);
        trip1.setTransports(Transport.values()[i]);
        trip1.setPrice((random.nextInt(1000)) + 100);
        return trip1;
    }
}
