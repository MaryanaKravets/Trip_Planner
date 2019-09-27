package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @ResponseBody
    @GetMapping("/all")
    public List<Place> findAllPlaces() {

        return placeService.findAllPlace();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Place getPlaceById(@PathVariable(name = "id") Long id) {

        return placeService.getById(id);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable("id") Long id) {
        placeService.deletePlaceById(id);

        return ResponseEntity.noContent().build();
    }
}
