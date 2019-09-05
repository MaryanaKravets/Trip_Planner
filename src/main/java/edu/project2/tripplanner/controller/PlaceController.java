package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.service.IPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/places")
public class PlaceController {

        private IPlaceService iPlaceService;

    @ResponseBody
    @GetMapping
    public List<Place> findAllPlaces() {

        return iPlaceService.findAllPlace();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Optional<Place> findPlaceById(@PathVariable(name = "id") Long id) {

        return iPlaceService.findPlaceById(id);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable("id") Long id) {
        iPlaceService.deletePlaceById(id);
    }
}
