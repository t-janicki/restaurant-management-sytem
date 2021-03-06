package com.restaurant.management.web.controller.ecommerce;

import com.restaurant.management.domain.ecommerce.dto.DailyOrderListDto;
import com.restaurant.management.domain.ecommerce.dto.OrderDto;
import com.restaurant.management.mapper.ecommerce.DailyOrderListMapper;
import com.restaurant.management.mapper.ecommerce.OrderMapper;
import com.restaurant.management.security.CurrentUser;
import com.restaurant.management.security.UserPrincipal;
import com.restaurant.management.service.ecommerce.facade.OrderFacade;
import com.restaurant.management.service.ecommerce.facade.OrderProcessor;
import com.restaurant.management.web.response.DailyOrderListResponse;
import com.restaurant.management.web.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderFacade orderFacade;
    private OrderMapper orderMapper;
    private OrderProcessor orderProcessor;
    private DailyOrderListMapper dailyOrderListMapper;

    @Autowired
    public OrderController(OrderFacade orderFacade,
                           OrderMapper orderMapper,
                           OrderProcessor orderProcessor,
                           DailyOrderListMapper dailyOrderListMapper) {
        this.orderFacade = orderFacade;
        this.orderMapper = orderMapper;
        this.orderProcessor = orderProcessor;
        this.dailyOrderListMapper = dailyOrderListMapper;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PagedResources<OrderResponse>> showOrdersPageable(@CurrentUser UserPrincipal currentUser,
                                                                     Pageable pageable,
                                                                     PagedResourcesAssembler assembler) {
        Page<OrderDto> ordersDto = orderFacade.getAllOrders(currentUser, pageable);

        Page<OrderResponse> responsePage = orderMapper.mapToOrderResponsePage(ordersDto);

        return new ResponseEntity<>(assembler.toResource(responsePage), HttpStatus.OK);
    }

    @GetMapping(value = "/count", produces = APPLICATION_JSON_VALUE)
    public Long countOrders(@CurrentUser UserPrincipal currentUser){
        return orderFacade.countRestaurantOrders(currentUser);
    }

    @GetMapping(value = "/{orderId}",
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    Resource<OrderResponse> showOrder(@CurrentUser UserPrincipal currentUser,
                                      @PathVariable Long orderId) {
        OrderDto orderDto = orderFacade.getByOrderNumber(currentUser, orderId);

        OrderResponse response = orderMapper.mapToOrderResponse(orderDto);

        Link link = linkTo(OrderController.class).slash(response.getId()).withSelfRel();

        return new Resource<>(response, link);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<?> deleteOrder(@CurrentUser UserPrincipal currentUser,
                                         @PathVariable Long orderId) {

        return ResponseEntity.ok().body(orderFacade.deleteOrder(currentUser, orderId));
    }

    @GetMapping(value = "/year", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PagedResources<OrderResponse>> getAllOfCurrentYear(@CurrentUser UserPrincipal currentUser,
                                                                      Pageable pageable,
                                                                      PagedResourcesAssembler assembler) {
        Page<OrderDto> ordersDto = orderFacade.getAllOfCurrentYear(currentUser, pageable);

        Page<OrderResponse> ordersResponse = orderMapper.mapToOrderResponsePage(ordersDto);

        return new ResponseEntity<>(assembler.toResource(ordersResponse), HttpStatus.OK);
    }

    @PostMapping(value = "/order/{customerId}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    Resource<DailyOrderListResponse> processOrder(@CurrentUser UserPrincipal currentUser,
                                         @PathVariable Long customerId) {
        DailyOrderListDto orderListDto = orderProcessor.processOrder(currentUser, customerId);

        DailyOrderListResponse response =  dailyOrderListMapper.mapToDailyOrderListResponse(orderListDto);

        Link link = linkTo(OrderController.class).slash(response.getId()).withSelfRel();

        return new Resource<>(response, link);
    }
}