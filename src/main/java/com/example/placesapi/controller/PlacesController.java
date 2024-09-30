package com.example.placesapi.controller;

import com.example.placesapi.model.Places;
import com.example.placesapi.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlacesController {

    @Autowired
    private PlacesService placesService;

    @GetMapping
    public ResponseEntity<List<Places>> getPlaces(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam int radius) {
        List<Places> places = placesService.getNearbyPlaces(longitude, latitude, radius);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
