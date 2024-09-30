package com.example.placesapi.repository;

import com.example.placesapi.model.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Long> {

    // Belirtilen enlem, boylam ve yarıçapla eşleşen yerleri bulma
    List<Places> findByLatitudeAndLongitudeAndRadius(double latitude, double longitude, double radius);

    // İsme göre belirtilen isim, enlem ve boylamla eşleşen yerleri bulma
    List<Places> findByNameAndLatitudeAndLongitude(String name, double latitude, double longitude);
}
