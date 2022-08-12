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

    @Operation(summary = "Get all items", operationId = "getAllItems", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "All items extracted successfully", content = {@Content(schema = @Schema(implementation = Items.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
            })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    Items getAllItems();

    @Operation(summary = "Get items available for sale", operationId = "getAvailableItems", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "All items available for sale extracted successfully", content = {@Content(schema = @Schema(implementation = Items.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
            })
    @GetMapping(path = "/available", produces = {MediaType.APPLICATION_XML_VALUE})
    Items getAvailableItems();

    @Operation(summary = "Get items page", operationId = "getItemPage", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item page extracted successfully", content = {@Content(schema = @Schema(implementation = Items.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(path = "/page/{index}/{offset}", produces = {MediaType.APPLICATION_XML_VALUE})
    Items getItemPage(@PathVariable Integer index, @PathVariable Integer offset);

    @Operation(summary = "Get most purchased item from last 30 days", operationId = "getMonthlyBestSellerItem", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Most purchased item name was extracted successfully", content = {@Content(schema = @Schema(implementation = String.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(path = "/best-seller", produces = {MediaType.TEXT_PLAIN_VALUE})
    String getMonthlyBestSellerItem();
}
