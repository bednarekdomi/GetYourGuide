package com.App.GetYourGuide.service;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.Order;
import com.App.GetYourGuide.repository.GuideRepository;
import com.App.GetYourGuide.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final GuideRepository guideRepository;
    private final EmailService emailService;
    private final OrderService orderService;

    public void createScheduleForGuide(LocalDate date, String scheduleFilePath) {
        List<Order> ordersThisWeek = orderService.filterOrdersForUpcomingWeek(date);
        List<Guide> allGuides = guideRepository.findAll();
        for (Guide guide : allGuides) {
            for (Order order : ordersThisWeek) {
                clearScheduleFile(scheduleFilePath);
                if (order.getGuide().equals(guide)) {
                    addTourToFile(scheduleFilePath, order.getTourDate().toString() + " " +
                            order.getCustomer().getName().toString());
                }
                emailService.sendEmailWithWeeklySchedule(guide.getEmail(), "path/to/weeklySchedule.txt");
            }
        }
    }

    public void clearScheduleFile(String filePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false));
            bw.write("");
            System.out.println("The file has been cleared");
        } catch (IOException e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }

    public void addTourToFile(String filePath, String line) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }
}
