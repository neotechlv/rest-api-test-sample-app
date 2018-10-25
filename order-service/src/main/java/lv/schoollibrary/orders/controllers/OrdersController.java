package lv.schoollibrary.orders.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lv.schoollibrary.orders.dtos.ErrorDto;
import lv.schoollibrary.orders.dtos.OrderInfoDto;
import lv.schoollibrary.orders.dtos.OrderRequestDto;
import lv.schoollibrary.orders.services.OrdersService;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping(path = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrdersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @ApiOperation(value = "Creates a new order")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "Returned when the order is created successfully",
                    response = OrderInfoDto.class
            ),
            @ApiResponse(code = 400,
                    message = "Returned when data is malformed",
                    response = ErrorDto.class)
    })
    @PostMapping
    public OrderInfoDto add(@Valid @RequestBody OrderRequestDto item) {
        LOGGER.debug("Adding order {}", item);
        return ordersService.addItem(item);
    }

    @ApiOperation(value = "Gets orders")
    @GetMapping
    public List<OrderInfoDto> allOrders(@ApiParam("Optional filter by schoolkid ID") @RequestParam(required = false) String schoolKidId) {
        LOGGER.debug("Retrieving all orders, schoolKid ID is {}", schoolKidId);
        return isBlank(schoolKidId) ? ordersService.allOrders() : ordersService.allKidsOrders(schoolKidId);
    }

    @ApiOperation(value = "Gets order by id")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "Returned when the order is found",
                    response = OrderInfoDto.class
            ),
            @ApiResponse(code = 400,
                    message = "Returned when ID is malformed or order not found (distinguished by error code)",
                    response = ErrorDto.class)
    })
    @GetMapping("/{id}")
    public OrderInfoDto getOrderById(@PathVariable String id) {
        LOGGER.debug("Get order by id {}", id);
        return ordersService.getById(id);
    }

    @ApiOperation(value = "Updates order info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned when the order is updated successfully", response = OrderInfoDto.class),
            @ApiResponse(code = 400, message = "Returned when data is malformed"),
    })
    @PutMapping("/{id}")
    public OrderInfoDto update(@PathVariable String id, @Valid @RequestBody OrderRequestDto item) {
        LOGGER.debug("Updating order {} to be {}", id, item);
        return ordersService.updateItem(id, item);
    }

    @ApiOperation(value = "Deletes an order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned when the order is deleted successfully"),
            @ApiResponse(code = 400, message = "Returned when the order doesn't exist"),
    })
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        LOGGER.debug("Deleting order {}", id);
        ordersService.deleteItem(id);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Request processing failed")
    public void handleGeneralError(Exception ex) {
        LOGGER.error("Request processing failed", ex);
    }


}
