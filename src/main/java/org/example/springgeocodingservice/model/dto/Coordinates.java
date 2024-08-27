package org.example.springgeocodingservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Geographical coordinates with additional descriptive information.")
public class Coordinates implements Serializable {

    @Schema(description = "Descriptive text for the coordinates. For example, it might be an address or a place name.",
            example = "Russia, Ulyanovsk, Sobornaya Square")
    private String text;

    @Schema(description = "Longitude of the geographical location. Represents the east-west position on the Earth's surface.",
            example = "48.4104")
    private Double longitude;

    @Schema(description = "Latitude of the geographical location. Represents the north-south position on the Earth's surface.",
            example = "54.3288")
    private Double latitude;
}
