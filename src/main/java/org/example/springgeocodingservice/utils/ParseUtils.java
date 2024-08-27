package org.example.springgeocodingservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springgeocodingservice.model.dto.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing geocoding JSON responses.
 * Provides methods to extract coordinates from the JSON response of the Yandex Geocoding API.
 */
public class ParseUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Parses the JSON response from the Yandex Geocoding API to extract a list of {@link Coordinates}.
     *
     * @param json the JSON response from the Yandex Geocoding API as a String.
     * @return a list of {@link Coordinates} extracted from the JSON response.
     *         Returns an empty list if no coordinates are found or if parsing fails.
     * @throws RuntimeException if there is an error parsing the JSON response.
     */
    public static List<Coordinates> parseCoordinates(String json) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        try {
            JsonNode node = mapper.readTree(json);
            JsonNode featureMembers = node.path("response")
                    .path("GeoObjectCollection")
                    .path("featureMember");

            for (JsonNode feature : featureMembers) {
                JsonNode point = feature.path("GeoObject")
                        .path("Point")
                        .path("pos");
                String[] coords = point.asText().split(" ");
                double lat = Double.parseDouble(coords[0]);
                double lng = Double.parseDouble(coords[1]);
                coordinatesList.add(new Coordinates(getText(feature), lat, lng));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse coordinates", e);
        }
        return coordinatesList;
    }

    /**
     * Extracts the text description from a given feature in the JSON response.
     *
     * @param feature the JsonNode representing a feature from the JSON response.
     * @return the text description associated with the feature.
     */
    private static String getText(JsonNode feature) {
        return feature.path("GeoObject")
                .path("metaDataProperty")
                .path("GeocoderMetaData")
                .path("text")
                .asText();
    }
}
