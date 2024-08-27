package org.example.springgeocodingservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detailed error response for API error handling.")
public class ErrorResponse {

    @Schema(description = "Timestamp of when the error occurred.",
            example = "2024-08-27T15:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code of the error response.",
            example = "500")
    private int status;

    @Schema(description = "Short description of the error.",
            example = "Internal Server Error")
    private String error;

    @Schema(description = "Detailed message about the error.",
            example = "An error occurred on the server. Please try again later.")
    private String message;

    @Schema(description = "The path of the request that caused the error.",
            example = "/geocode/address")
    private String path;
}
