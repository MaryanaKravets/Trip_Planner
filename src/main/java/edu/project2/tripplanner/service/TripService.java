package edu.project2.tripplanner.service;

import edu.project2.tripplanner.exception.NotFoundExceptions;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Transport;
import edu.project2.tripplanner.model.Trip;
import edu.project2.tripplanner.repository.CommentRepository;
import edu.project2.tripplanner.repository.PlaceRepository;
import edu.project2.tripplanner.repository.TripRepository;
import edu.project2.tripplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class TripService implements ITripService {

    private TripRepository tripRepository;
    private PlaceRepository placeRepository;
    private UserRepository userRepository;


    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteTripById(id);
    }

    @Override
    public Trip deletePlaceFromTrip(Long userId, Long tripId, Long placeId) {
        Place place = placeRepository.findById(placeId).get();
        return tripRepository.findTripByIdAndUserId(userId, tripId)
                .map(t -> {
                    t.removePlace(place);
                    placeRepository.save(place);
                    return tripRepository.save(t);
                })
                .orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public ResponseEntity<Trip> addTrip(Long userId, Trip trip) {
        userRepository.findById(userId).map(u -> {
            trip.setDepartureDay(trip.getDepartureDay());
            trip.setDayOfArrival(trip.getDayOfArrival());
            Random random = new Random();
            int i = random.nextInt(Transport.values().length);
            trip.setTransports(Transport.values()[i]);
            trip.setPrice((random.nextInt(1000)) + 100);
            u.getTripList().add(trip);
            trip.setUser(u);
            return tripRepository.save(trip);
        });
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Override
    public Trip editTrip(Long userId, Long tripId, Trip trip) {
        if (!userRepository.existsById(userId) | !tripRepository.existsById(tripId)) {
            throw new NotFoundExceptions();
        }
        return tripRepository.findTripByIdAndUserId(userId, tripId).map(t -> {
            t.setDepartureDay(trip.getDepartureDay());
            t.setDayOfArrival(trip.getDayOfArrival());
            Random random = new Random();
            int i = random.nextInt(Transport.values().length);
            t.setTransports(Transport.values()[i]);
            t.setPrice((random.nextInt(1000)) + 100);
            return tripRepository.save(trip);
        }).orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public List<Trip> findAllByUserId(Long userId) {
        return tripRepository.findAllByUserId(userId);
    }

    @Override
    public Trip addPlaceToTrip(Long userId, Long tripId, Long placeId) {
        Place place = placeRepository.findById(placeId).get();
        return tripRepository.findTripByIdAndUserId(userId, tripId).map(t -> {
            t.addPlace(place);
            placeRepository.save(place);
            return tripRepository.save(t);
        })
                .orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId) {
        return tripRepository.findTripByIdAndUserId(userId, tripId);
    }
}
