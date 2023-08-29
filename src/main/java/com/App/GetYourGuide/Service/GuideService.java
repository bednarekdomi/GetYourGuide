package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.GuideMapper;
import com.App.GetYourGuide.Repository.GuideRepository;
import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import com.App.GetYourGuide.domain.OrderDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
            for (OrderDetails order : guide.getTours()) {
                if (!order.getTourDate().equals(date) || guide.getDaysOffSinceLastTrip() >= 2) {
                    availableGuides.add(guide);
                }
            }
        }

        return availableGuides;
    }

}
