package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Place> findPlaceById(@PathVariable(name = "id") Long id) {

        return placeService.findPlaceById(id);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable("id") Long id) {
        placeService.deletePlaceById(id);
    }
}
