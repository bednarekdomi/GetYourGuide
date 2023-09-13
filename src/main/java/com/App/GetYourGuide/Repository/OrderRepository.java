package com.App.GetYourGuide.Repository;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order setGuideToOrder(LocalDate date, List<Guide> availableGuides){
        Order orderToCreate = new Order();
        orderToCreate.setGuide(availableGuides.get(0));
        orderToCreate.setTourDate(date);
        save(orderToCreate);
        return orderToCreate;
    }
}
