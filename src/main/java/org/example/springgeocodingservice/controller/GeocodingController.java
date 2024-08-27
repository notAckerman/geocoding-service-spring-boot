package org.example.springgeocodingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.springgeocodingservice.model.dto.Coordinates;
import org.example.springgeocodingservice.service.GeocodingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Geocoding API", description = "API for geocoding addresses and coordinates.")
public class GeocodingController {

    private final GeocodingService geocodingService;

    @GetMapping("/geocode/address")
    @Operation(summary = "Geocode address", description = "Retrieve coordinates for a given address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of coordinates", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coordinates.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<Coordinates>> getCoordinates(
            @Parameter(description = "Address to geocode", example = "Russia, Ulyanovsk, Sobornaya Square") @RequestParam("address") String address) {
        return new ResponseEntity<>(geocodingService.geocode(address), HttpStatus.OK);
    }

    @GetMapping("/geocode/coordinates")
    @Operation(summary = "Geocode coordinates", description = "Retrieve coordinates for a given latitude and longitude.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of coordinates", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coordinates.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<Coordinates>> getCoordinates(
            @Parameter(description = "Latitude to geocode", example = "54.3288") @RequestParam("latitude") Double latitude,
            @Parameter(description = "Longitude to geocode", example = "48.4104") @RequestParam("longitude") Double longitude) {
        return new ResponseEntity<>(geocodingService.geocode(latitude, longitude), HttpStatus.OK);
    }
}
