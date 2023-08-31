package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.OrderDetailsMapper;
import com.App.GetYourGuide.Repository.OrderDetailsRepository;
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
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final EmailService emailService;
    private final GuideService guideService;

    public Optional<OrderDetailsDto> getOrder(long orderId) {
        return orderDetailsMapper.mapToOrderDetailsDto(orderDetailsRepository.findById(orderId));
    }

    public List<OrderDetailsDto> getAllOrders(){
        return orderDetailsMapper.mapToOrderDetailsDtoList(orderDetailsRepository.findAll());
    }

    public void deleteOrder(Long orderId) {
        orderDetailsRepository.deleteById(orderId);
    }

    public void createOrder(Customer customer, LocalDate date) {
        List<Guide> availableGuide = guideService.getAvailableGuides(date);
        if (availableGuide.isEmpty()) {
            System.out.println("No guides available for this day");
        } else {
           OrderDetails newOrder = orderDetailsRepository.setGuideToOrder(date, availableGuide);
            emailService.sendMailAfterCreatingOrder(newOrder);
        }
    }

}
