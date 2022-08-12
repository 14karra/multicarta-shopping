package ru.multicarta.shopping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicarta.shopping.dto.Customers;
import ru.multicarta.shopping.exception.ApiError;

@RequestMapping(value = "/api/v1/customer")
public interface CustomerApi {

    @Operation(summary = "Get all customers", operationId = "getAllCustomers", tags = {"Customer"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "All customers extracted successfully", content = {@Content(schema = @Schema(implementation = Customers.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
            })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    Customers getAllCustomers();

    @Operation(summary = "Get customers page", operationId = "getCustomerPage", tags = {"Customer"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer page extracted successfully", content = {@Content(schema = @Schema(implementation = Customers.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(path = "/page/{index}/{offset}", produces = {MediaType.APPLICATION_XML_VALUE})
    Customers getCustomerPage(@PathVariable Integer index, @PathVariable Integer offset);

    @Operation(summary = "Get customer with most purchased from last 6 months", operationId = "get6MonthsBestCustomer", tags = {"Item"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer with most purchased from last 6 months was extracted successfully", content = {@Content(schema = @Schema(implementation = String.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ApiError.class))})
            })
    @GetMapping(path = "/best-customer", produces = {MediaType.TEXT_PLAIN_VALUE})
    String get6MonthsBestCustomer();
}
