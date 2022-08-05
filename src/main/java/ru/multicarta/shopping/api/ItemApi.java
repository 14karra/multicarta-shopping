package ru.multicarta.shopping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicarta.shopping.dto.Items;
import ru.multicarta.shopping.exception.ApiError;

@RequestMapping(value = "/api/v1/item")
public interface ItemApi {

    @Operation(summary = "Get items page", operationId = "getItemPage", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item page extracted successfully", content = {@Content(schema = @Schema(implementation = Items.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(path = "/page/{index}/{offset}", produces = {MediaType.APPLICATION_XML_VALUE})
    Items getItemPage(@PathVariable Integer index, @PathVariable Integer offset);
}
