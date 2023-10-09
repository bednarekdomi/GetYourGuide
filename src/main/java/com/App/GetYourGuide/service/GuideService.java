package com.App.GetYourGuide.service;

import com.App.GetYourGuide.mapper.GuideMapper;
import com.App.GetYourGuide.repository.GuideRepository;
import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import com.App.GetYourGuide.domain.Order;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public List<GuideDto> getAllGuides() {
        return guideMapper.mapToGuideDtoList(guideRepository.findAll());
    }

    public List<Guide> getAvailableGuides(LocalDate date) {
        List<Guide> availableGuides = new ArrayList<>();
        List<Guide> allGuides = guideRepository.findAll();
        for (Guide guide : allGuides) {
            for (Order order : guide.getTours()) {
                if (!order.getTourDate().equals(date) || getDaysOffSinceLastTrip(guide.getId()) >= 2) {
                    availableGuides.add(guide);
                }
            }
        }

        return availableGuides.stream().sorted().toList();
    }

    public long getDaysOffSinceLastTrip(long guideId) {
        Guide guide = guideRepository.getReferenceById(guideId);
        List<Order> tours = guide.getTours();
        LocalDate lastTripDate = LocalDate.MIN;
        for (Order order : tours) {
            LocalDate tourDate = order.getTourDate();
            if (tourDate.isBefore(LocalDate.now()) && tourDate.isAfter(lastTripDate)) {
                lastTripDate = tourDate;
            }
        }
        return ChronoUnit.DAYS.between(lastTripDate, LocalDate.now());
    }
}
