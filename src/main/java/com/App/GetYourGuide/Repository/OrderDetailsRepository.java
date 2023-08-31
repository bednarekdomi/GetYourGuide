package com.App.GetYourGuide.Repository;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    default void setGuideToOrder(LocalDate date, List<Guide> availableGuides){
        OrderDetails orderToCreate = new OrderDetails();
        orderToCreate.setGuide(availableGuides.get(0));
        orderToCreate.setTourDate(date);
        save(orderToCreate);
    }
}
