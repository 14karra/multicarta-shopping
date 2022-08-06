package ru.multicarta.shopping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.multicarta.shopping.dto.PurchaseRequest;
import ru.multicarta.shopping.dto.Purchases;
import ru.multicarta.shopping.entity.Purchase;
import ru.multicarta.shopping.exception.ApiError;

@RequestMapping(value = "/api/v1/purchase")
public interface PurchaseApi {

    @Operation(summary = "Perform purchase", operationId = "getItemPage", tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Purchase performed successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE})
    void postPerformPurchase(@RequestBody PurchaseRequest purchaseRequest);

    @Operation(summary = "Get purchases from last n days", operationId = "getPurchasesFromPassedDays", tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Last week purchases extracted successfully", content = {@Content(schema = @Schema(implementation = Purchases.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    Purchases getPurchasesFromPassedDays(@RequestParam(name = "days", required = false, defaultValue = "7") int daysPassed);

}
