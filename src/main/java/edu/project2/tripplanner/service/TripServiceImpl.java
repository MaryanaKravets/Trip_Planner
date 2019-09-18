package edu.project2.tripplanner.service;

import edu.project2.tripplanner.dto.TripDTO;
import edu.project2.tripplanner.dto.TripIdDTO;
import edu.project2.tripplanner.exception.Message;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Transport;
import edu.project2.tripplanner.model.Trip;
import edu.project2.tripplanner.repository.PlaceRepository;
import edu.project2.tripplanner.repository.TripRepository;
import edu.project2.tripplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService, Message {

    private final TripRepository tripRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    private Long userId;
    private Long placeId;
    private Long tripId;

    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteTripById(id);
    }

    @Override
    public Trip deletePlaceFromTrip(TripIdDTO tripIdDTO) {
         userId=tripIdDTO.getUserId();
         placeId=tripIdDTO.getPlaceId();
         tripId=tripIdDTO.getTripId();
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()->new NotFoundException(String.format(mes4, placeId)));
        return tripRepository.findTripByIdAndUserId(userId, tripId)
                .map(t -> {
                    t.removePlace(place);
                    placeRepository.save(place);
                    return tripRepository.save(t);
                })
                .orElseThrow(()->new NotFoundException(String.format(mes5, tripId,userId)));
    }

    @Override
    public ResponseEntity<Trip> addTrip(Long userId, Trip trip) {
        userRepository.findById(userId).map(u -> {
            Trip trip1=createTrip(trip);
            u.getTripList().add(trip1);
            trip1.setUser(u);
            return tripRepository.save(trip1);
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Trip editTrip(TripDTO tripDTO) {
         userId=tripDTO.getUserId();
         tripId=tripDTO.getTripId();
        if (!userRepository.existsById(userId) || !tripRepository.existsById(tripId)) {
            throw new NotFoundException(String
                    .format(mes5,tripId,userId));
        }
        return tripRepository.findTripByIdAndUserId(userId, tripId)
                .map(t -> tripRepository.save(createTrip(t)))
                .orElseThrow(()->new NotFoundException(String.format(mes5,tripId,userId)));
    }

    @Override
    public List<Trip> findAllByUserId(Long userId) {

        return tripRepository.findAllByUserId(userId);
    }

    @Override
    public Trip addPlaceToTrip(TripIdDTO tripIdDTO) {
         userId=tripIdDTO.getUserId();
         placeId=tripIdDTO.getPlaceId();
         tripId=tripIdDTO.getTripId();
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()->new NotFoundException(String.format(mes4,placeId)));
        return tripRepository.findTripByIdAndUserId(userId, tripId).map(t -> {
            t.addPlace(place);
            placeRepository.save(place);

            return tripRepository.save(t);
        })
                .orElseThrow(()->new NotFoundException(String.format(mes5, tripId,userId)));
    }

    @Override
    public Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId) {

        return tripRepository.findTripByIdAndUserId(userId, tripId);
    }

    private Trip createTrip(Trip trip){
        Trip trip1=new Trip();
        trip1.setDepartureDay(trip.getDepartureDay());
        trip1.setDayOfArrival(trip.getDayOfArrival());
        Random random = new Random();
        int i = random.nextInt(Transport.values().length);
        trip1.setTransports(Transport.values()[i]);
        trip1.setPrice((random.nextInt(1000)) + 100);
        return trip1;
    }
}
