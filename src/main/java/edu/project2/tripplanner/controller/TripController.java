package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Trip;
import edu.project2.tripplanner.service.ITripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class TripController {

    ITripService iTripService;

    @ResponseBody
    @GetMapping("/trip/{userId}")
    public List<Trip> findTripsByUserId(@PathVariable(value = "userId") Long userId) {

        return iTripService.findAllByUserId(userId);
    }


    @ResponseBody
    @PostMapping("/trip")
    public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
        iTripService.addTrip(trip);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @ResponseBody
    @PostMapping("/user/{userId}/trip/{tripId}/place/{placeId}")
    public ResponseEntity<Place> addPlaceToTrip(@PathVariable(name = "userId") Long userId,
                                                @PathVariable(name = "tripId") Long tripId,
                                                @PathVariable(name = "placeId") Long placeId){

       return iTripService.addPlaceToTrip(userId, tripId, placeId);
    }


    @ResponseBody
    @DeleteMapping("/trip/{tripId}")
    public ResponseEntity<Boolean> deleteTrip(@PathVariable("tripId") Long tripId)
    {
        iTripService.deleteTripById(tripId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @ResponseBody
    @DeleteMapping("/user/{userId}/trip/{tripId}/place/{placeId}")
    public ResponseEntity<Place> deletePlaceFromTrip(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "tripId") Long tripId,
            @PathVariable(name = "placeId") Long placeId)
    {
       return iTripService.deletePlaceFromTrip(userId, tripId, placeId);
    }


    @ResponseBody
    @PatchMapping("/user/{userId}/trip/{tripId}")
    public Trip updateTrip(@PathVariable(name = "userId") Long userId,
                           @PathVariable(name = "tripId") Long tripId,
                           @RequestBody Trip trip) {
        return iTripService.editTrip(userId, tripId, trip);
    }

}
