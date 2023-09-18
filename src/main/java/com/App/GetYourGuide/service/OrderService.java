package com.App.GetYourGuide.service;

import com.App.GetYourGuide.mapper.OrderMapper;
import com.App.GetYourGuide.repository.OrderRepository;
import com.App.GetYourGuide.domain.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmailService emailService;
    private final GuideService guideService;

    public Optional<OrderDto> getOrder(long orderId) {
        return orderMapper.mapToOrderDetailsDto(orderRepository.findById(orderId));
    }

    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDetailsDtoList(orderRepository.findAll());
    }

    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        if (orderRepository.getReferenceById(orderId).getTourDate().isBefore(LocalDate.now().minusDays(2)))
            emailService.earlyCancellationEmail(orderRepository.getReferenceById(orderId));
        emailService.laterCancellationEmail(orderRepository.getReferenceById(orderId));
    }

    public void createOrder(Customer customer, LocalDate date) {
        List<Guide> availableGuide = guideService.getAvailableGuides(date);
        if (availableGuide.isEmpty()) System.out.println("No guides available for this day");
        Order newOrder = new Order();
        newOrder.setTourDate(date);
        newOrder.setGuide(availableGuide.get(0));
        newOrder.setCustomer(customer);
        emailService.sendEmailAfterCreatingOrder(newOrder);
    }

    public OrderDto updateOrderDetails(Long orderId, LocalDate newDate) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) throw new IllegalArgumentException("Order with ID " + orderId + " not found");

        Order order = optionalOrder.get();
        order.setTourDate(newDate);
        return orderMapper.mapToOrderDetailsDto(orderRepository.save(order));

    }

    public double refundPayment(Long orderId) {
        return orderRepository.getReferenceById(orderId).getOrderDecorator().getCost();
    }
}
