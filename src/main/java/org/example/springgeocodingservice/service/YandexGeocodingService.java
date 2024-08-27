package org.example.springgeocodingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springgeocodingservice.model.dto.Coordinates;
import org.example.springgeocodingservice.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service implementation for geocoding using Yandex Geocoding API.
 * This service communicates with Yandex's geocoding API to retrieve coordinates
 * based on address or geographic coordinates (latitude and longitude).
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class YandexGeocodingService implements GeocodingService {

    private final String URL = "https://geocode-maps.yandex.ru/1.x/";

    @Value("${yandex.api.key}")
    private final String API_KEY;

    private final RestTemplate restTemplate;

    /**
     * Geocodes the given address and retrieves coordinates for it.
     *
     * @param address the address to be geocoded (e.g., "Russia, Ulyanovsk, Sobornaya Square").
     * @return a list of {@link Coordinates} corresponding to the given address.
     *         If no coordinates are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "coordinates", key = "#address")
    public List<Coordinates> geocode(String address) {
        String endpoint = URL + "?format=json&geocode=" + address + "&apikey=" + API_KEY + "&lang=ru_RU";
        String json = restTemplate.getForObject(endpoint, String.class);
        return ParseUtils.parseCoordinates(json);
    }

    /**
     * Geocodes the given latitude and longitude and retrieves coordinates.
     *
     * @param lat the latitude to be geocoded (e.g., 54.3288).
     * @param lon the longitude to be geocoded (e.g., 48.4104).
     * @return a list of {@link Coordinates} corresponding to the given latitude and longitude.
     *         If no coordinates are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "coordinates", key = "#lon + ',' + #lat")
    public List<Coordinates> geocode(Double lat, Double lon) {
        String endpoint = URL + "?format=json&geocode=" + lon + " " + lat + "&apikey=" + API_KEY + "&lang=ru_RU";
        String json = restTemplate.getForObject(endpoint, String.class);
        return ParseUtils.parseCoordinates(json);
    }
}
