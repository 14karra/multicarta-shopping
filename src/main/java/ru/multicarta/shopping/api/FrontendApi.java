package ru.multicarta.shopping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface FrontendApi {

    @Operation(summary = "Redirect to website", operationId = "redirect", tags = {"Website"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Website accessed successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            })
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    String redirect();
}
