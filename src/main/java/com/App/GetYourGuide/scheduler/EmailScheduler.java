package com.App.GetYourGuide.scheduler;

import com.App.GetYourGuide.domain.Order;
import com.App.GetYourGuide.repository.OrderRepository;
import com.App.GetYourGuide.service.EmailService;
import com.App.GetYourGuide.service.ScheduleService;
import lombok.RequiredArgsConstructor;
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
    private final ScheduleService scheduleService;


    @Scheduled(cron = "0 0 10 * * *")
    public void sendReminderEmail() {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> upcomingOrders = allOrders.stream().filter(o -> o.getTourDate().isAfter(LocalDate.now())).toList();

        for (Order order : upcomingOrders) {
            LocalDate tourDate = order.getTourDate();
            long daysUntilTour = ChronoUnit.DAYS.between(LocalDate.now(), tourDate);
            emailService.sendReminderEmailToClient(order, daysUntilTour);
        }
    }
    @Scheduled(cron = "0 0 18 * * SUN")
    public void sendWeeklySchedule(){
        scheduleService.createScheduleForGuide(LocalDate.now(), "pathTo/weeklySchedule.txt");
    }

}
