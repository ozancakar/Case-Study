import React, { useState } from 'react';
import axios from 'axios';
import { GoogleMap, Marker, useLoadScript } from '@react-google-maps/api';

const App = () => {
  const [longitude, setLongitude] = useState('');
  const [latitude, setLatitude] = useState('');
  const [radius, setRadius] = useState('');
  const [places, setPlaces] = useState([]);

  // Google Maps API yükleme
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY,

  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get('http://localhost:8070/places', {
        params: {
          longitude,
          latitude,
          radius
        }
      });
      setPlaces(response.data.results); // Verileri kaydet
    } catch (error) {
      console.error("Error fetching places", error);
    }
  };

  if (!isLoaded) return <div>Loading...</div>;

  return (
    <div>
      <h1>Yakındaki Yerleri Bul</h1>
      <form onSubmit={handleSubmit}>
        <label>Boylam:</label>
        <input
          type="text"
          value={longitude}
          onChange={(e) => setLongitude(e.target.value)}
          required
        />
        <label>Enlem:</label>
        <input
          type="text"
          value={latitude}
          onChange={(e) => setLatitude(e.target.value)}
          required
        />
        <label>Yarıçap (metre):</label>
        <input
          type="text"
          value={radius}
          onChange={(e) => setRadius(e.target.value)}
          required
        />
        <button type="submit">Ara</button>
      </form>

      {/* Google Map */}
      <GoogleMap
        center={{ lat: parseFloat(latitude), lng: parseFloat(longitude) }}
        zoom={12}
        mapContainerStyle={{ width: '100%', height: '400px' }}
      >
        {places.map((place, index) => (
          <Marker
            key={index}
            position={{
              lat: place.geometry.location.lat,
              lng: place.geometry.location.lng
            }}
          />
        ))}
      </GoogleMap>
    </div>
  );
};

export default App;
