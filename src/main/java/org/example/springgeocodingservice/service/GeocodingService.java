package org.example.springgeocodingservice.service;

import org.example.springgeocodingservice.model.dto.Coordinates;

import java.util.List;

/**
 * Service interface for geocoding operations.
 * Provides methods for converting addresses and geographic coordinates into a list of {@link Coordinates}.
 */
public interface GeocodingService {

    /**
     * Geocodes the given address and retrieves coordinates for it.
     *
     * @param address the address to be geocoded (e.g., "Russia, Ulyanovsk, Sobornaya Square")
     * @return a list of {@link Coordinates} corresponding to the given address.
     *         If no coordinates are found, an empty list is returned.
     */
    List<Coordinates> geocode(String address);

    /**
     * Geocodes the given latitude and longitude and retrieves coordinates.
     *
     * @param lat the latitude to be geocoded (e.g., 54.3288)
     * @param lon the longitude to be geocoded (e.g., 48.4104)
     * @return a list of {@link Coordinates} corresponding to the given latitude and longitude.
     *         If no coordinates are found, an empty list is returned.
     */
    List<Coordinates> geocode(Double lat, Double lon);
}
