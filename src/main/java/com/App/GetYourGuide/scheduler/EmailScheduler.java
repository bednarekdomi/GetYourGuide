package com.App.GetYourGuide.scheduler;

import com.App.GetYourGuide.domain.MailDetails;
import com.App.GetYourGuide.domain.Order;
import com.App.GetYourGuide.repository.OrderRepository;
import com.App.GetYourGuide.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EmailScheduler {

    private final EmailService emailService;
    private final OrderRepository orderRepository;

    private static final String SUBJECT = "Reminder about upcoming mountain tour";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendReminderEmail() {
        List<Order> allOrders = orderRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Order order : allOrders) {
            LocalDate tourDate = order.getTourDate();
            long daysUntilTour = ChronoUnit.DAYS.between(currentDate, tourDate);

            if (daysUntilTour > 0) {
                emailService.sendEmail(new MailDetails(order.getCustomer().getEmail(),
                        SUBJECT,
                        "We would like to remind you about the upcoming mountain trip with a guide " + order.getGuide().getName()
                                + "in " + daysUntilTour + " days"
                ));
            }
        }
    }

}
