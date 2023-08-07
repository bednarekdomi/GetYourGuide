package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.Service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/order")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
}
