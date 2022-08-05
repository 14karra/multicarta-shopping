package ru.multicarta.shopping.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    @JsonProperty("code")
    @Schema(example = "123", required = true, description = "Error code")
    private String code;

    @JsonProperty("message")
    @Schema(example = "Invalid field value", required = true, description = "Error message")
    private String message;
}