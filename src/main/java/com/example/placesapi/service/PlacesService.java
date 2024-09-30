package com.example.placesapi.service;

import com.example.placesapi.model.GooglePlacesResponse;
import com.example.placesapi.model.Places;
import com.example.placesapi.repository.PlacesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class PlacesService {

    private final PlacesRepository placesRepository;
    private final String apiKey = "AIzaSyAeNTlFrIEbSSfnVHSH0FK5_tud1eKa1u4"; // Google API anahtarını application.properties'e taşı

    public PlacesService(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public List<Places> getNearbyPlaces(double longitude, double latitude, int radius) {
        // Veritabanında sorguyu kontrol et
        List<Places> cachedPlaces = placesRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);
        if (!cachedPlaces.isEmpty()) {
            return cachedPlaces;
        }

        // Google Places API'den veri al
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                + latitude + "," + longitude
                + "&radius=" + radius
                + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GooglePlacesResponse> response = restTemplate.getForEntity(url, GooglePlacesResponse.class);

        // Gelen verileri kontrol et ve kaydet
        List<Places> places = response.getBody().getResults();
        for (Places place : places) {
            // Veritabanında aynı yer var mı kontrol et
            List<Places> existingPlaces = placesRepository.findByNameAndLatitudeAndLongitude(
                    place.getName(), place.getLatitude(), place.getLongitude());

            if (existingPlaces.isEmpty()) {
                // Eğer aynı yer yoksa kaydet
                placesRepository.save(place);
            }
        }
        return places;
    }
}
