package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.Service.OrderDetailsService;
import com.App.GetYourGuide.domain.Customer;
import com.App.GetYourGuide.domain.OrderDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/order")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrderForTheDate(@RequestParam Customer customer, LocalDate date) {
        orderDetailsService.createOrder(customer, date);
        return ResponseEntity.ok("Order placed successfully");
    }
    @GetMapping("/getOrderById")
    public Optional<OrderDetailsDto> getOrderById(@RequestParam long orderId){
        return orderDetailsService.getOrder(orderId);
    }
    @GetMapping("/deleteOrderById")
    public void deleteOrderById(@RequestParam long orderId){
        orderDetailsService.deleteOrder(orderId);
    }




}
