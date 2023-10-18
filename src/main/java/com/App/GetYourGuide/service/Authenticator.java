package com.App.GetYourGuide.service;

import com.App.GetYourGuide.domain.Order;
import com.App.GetYourGuide.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Authenticator {

    public final OrderRepository orderRepository;

    public void authenticate(long orderId){
        Optional<Order> orderToAuthenticate = orderRepository.findById(orderId);
        orderToAuthenticate.ifPresent(order -> order.setVerified(true));
    }
}
