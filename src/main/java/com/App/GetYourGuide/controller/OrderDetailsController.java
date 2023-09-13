package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.service.OrderService;
import com.App.GetYourGuide.domain.Customer;
import com.App.GetYourGuide.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/order")
public class OrderDetailsController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrderForTheDate(@RequestParam Customer customer, LocalDate date) {
        orderService.createOrder(customer, date);
        return ResponseEntity.ok("Order placed successfully");
    }
    @GetMapping("/getOrderById")
    public Optional<OrderDto> getOrderById(@RequestParam long orderId){
        return orderService.getOrder(orderId);
    }
    @GetMapping("/deleteOrderById")
    public void deleteOrderById(@RequestParam long orderId){
        orderService.deleteOrder(orderId);
    }


    @PutMapping("/changeTourDate")
    public OrderDto updateTourDate(Long orderId, LocalDate newDate){
        return orderService.updateOrderDetails(orderId, newDate);
    }

}
