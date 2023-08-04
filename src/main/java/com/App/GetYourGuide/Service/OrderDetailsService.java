package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.OrderDetailsMapper;
import com.App.GetYourGuide.Repository.OrderDetailsRepository;
import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;

        public void createOrder(LocalDate date) {
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
            }
        }

}
