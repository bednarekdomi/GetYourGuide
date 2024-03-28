package com.App.GetYourGuide.service;

import com.App.GetYourGuide.mapper.GuideMapper;
import com.App.GetYourGuide.repository.GuideRepository;
import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import com.App.GetYourGuide.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public List<GuideDto> getAllGuides() {
        return guideMapper.mapToGuideDtoList(guideRepository.findAll());
    }

    public List<Guide> getAvailableGuides(LocalDate date) {
        Comparator<Guide> compareByDaysOff = Comparator.comparingLong(g -> getDaysOffSinceLastTrip(g.getId()));

        return guideRepository.findAll().stream().filter(g -> g.getTours().stream().noneMatch(o -> o.getTourDate().isEqual(date)) &&
                getDaysOffSinceLastTrip(g.getId()) < 2).sorted(compareByDaysOff.reversed()).toList();
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
