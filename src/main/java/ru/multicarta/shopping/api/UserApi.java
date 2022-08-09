package ru.multicarta.shopping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicarta.shopping.dto.Users;
import ru.multicarta.shopping.exception.ApiError;

@RequestMapping(value = "/api/v1/user")
public interface UserApi {

    @Operation(summary = "Register a user", operationId = "postUserRegistrationRequest", tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @PostMapping(path = "/register", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE})
    void postUserRegistrationRequest(@RequestBody() String userRegistrationRequestXml);

    @Operation(summary = "Get all users", operationId = "getAllUsers", tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "All users fetched successfully", content = {@Content(schema = @Schema(implementation = Users.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    Users getAllUsers();
}
