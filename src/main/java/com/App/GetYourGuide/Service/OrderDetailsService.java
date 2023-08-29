package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.OrderDetailsMapper;
import com.App.GetYourGuide.Repository.OrderDetailsRepository;
import com.App.GetYourGuide.domain.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Data
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final EmailService emailService;

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
        List<Guide> availableGuide = new ArrayList<>();
        OrderDetails orderToCreate = new OrderDetails();
        List<OrderDetails> allOrders = orderDetailsRepository.findAll();
        for (OrderDetails order : allOrders) {
            if (order.getTourDate() != date) {
                availableGuide.add(order.getGuide());
            }
        }

        if (availableGuide.isEmpty()) {
            System.out.println("No guides available for this day");
        } else {
            availableGuide.sort(Comparator.comparingLong(Guide::getDaysOffSinceLastTrip).reversed());
            orderToCreate.setGuide(availableGuide.get(0));
            orderToCreate.setTourDate(date);
            orderDetailsRepository.save(orderToCreate);
            emailService.sendEmail(new MailDetails(
              customer.getEmail(),
                    "A new order has been created",
                    "Hello " + customer.getName() + "!" + "/n" + "A new order number " + orderToCreate.getOrderId()
                            + "has been created. You can pay for your order within two days otherwise it will be cancelled"
            ));
        }
    }

    public List<GuideDto>availableGuides(LocalDate date){
        List<GuideDto> availableGuide = new ArrayList<>();
        List<OrderDetails> allOrders = orderDetailsRepository.findAll();
        for (OrderDetails order : allOrders) {
            if (order.getTourDate() != date) {
                availableGuide.add(order.getGuide());
            }
        }
            return availableGuide;
        }

}
