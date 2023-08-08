package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.Service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/order")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrderForTheDate(@RequestParam LocalDate date) {
        orderDetailsService.createOrder(date);
        return ResponseEntity.ok("Order placed successfully");
    }
}
