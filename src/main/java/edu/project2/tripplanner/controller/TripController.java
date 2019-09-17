package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.dto.TripDTO;
import edu.project2.tripplanner.dto.TripIdDTO;
import edu.project2.tripplanner.model.Trip;
import edu.project2.tripplanner.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @ResponseBody
    @GetMapping("/{userId}")
    public List<Trip> findTripsByUserId(@PathVariable(value = "userId") Long userId) {

        return tripService.findAllByUserId(userId);
    }

    @ResponseBody
    @PostMapping("/{userId}")
    public ResponseEntity<Trip> saveTrip(@PathVariable(name = "userId") Long userId,
                                         @RequestBody Trip trip) {
        return tripService.addTrip(userId, trip);
    }

    @ResponseBody
    @PostMapping
    public Trip addPlaceToTrip(@RequestBody TripIdDTO tripIdDTO){

        return tripService.addPlaceToTrip(tripIdDTO);
    }

    @ResponseBody
    @DeleteMapping("/{tripId}")
    public ResponseEntity<Boolean> deleteTrip(@PathVariable("tripId") Long tripId) {
        tripService.deleteTripById(tripId);

        return ResponseEntity

                .status(HttpStatus.OK)
                .build();
    }

    @ResponseBody
    @DeleteMapping
    public Trip deletePlaceFromTrip(@RequestBody TripIdDTO tripIdDTO){

        return tripService.deletePlaceFromTrip(tripIdDTO);
    }

    @ResponseBody
    @PatchMapping
    public Trip updateTrip(@RequestBody TripDTO tripDTO) {

        return tripService.editTrip(tripDTO);
    }
}
