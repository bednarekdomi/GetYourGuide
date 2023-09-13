package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.OrderMapper;
import com.App.GetYourGuide.Repository.OrderRepository;
import com.App.GetYourGuide.domain.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void createOrder(Customer customer, LocalDate date) {
        List<Guide> availableGuide = guideService.getAvailableGuides(date);
        if (availableGuide.isEmpty()) System.out.println("No guides available for this day");
        Order newOrder = orderRepository.setGuideToOrder(date, availableGuide);
        emailService.sendMailAfterCreatingOrder(newOrder);
    }

    public OrderDto updateOrderDetails(Long orderId, LocalDate newDate) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) throw new IllegalArgumentException("Order with ID " + orderId + " not found");

        Order order = optionalOrder.get();
        order.setTourDate(newDate);
        return orderMapper.mapToOrderDetailsDto(orderRepository.save(order));

    }
}
