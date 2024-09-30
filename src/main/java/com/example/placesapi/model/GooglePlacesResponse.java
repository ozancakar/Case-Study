package com.example.placesapi.model;

import java.util.ArrayList;
import java.util.List;

public class GooglePlacesResponse {
    private List<Places> results;

    // Constructor
    public GooglePlacesResponse() {
        this.results = new ArrayList<>();
    }

    public GooglePlacesResponse(List<Places> results) {
        this.results = results != null ? results : new ArrayList<>();
    }

    // Getter
    public List<Places> getResults() {
        return results;
    }

    // Setter
    public void setResults(List<Places> results) {
        this.results = results != null ? results : new ArrayList<>();
    }

    // toString Metodu
    @Override
    public String toString() {
        return "GooglePlacesResponse{" +
                "results=" + results +
                '}';
    }
}
