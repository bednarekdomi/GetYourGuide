package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.GuideMapper;
import com.App.GetYourGuide.Repository.GuideRepository;
import com.App.GetYourGuide.domain.GuideDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public List<GuideDto> getAllGuides() {
        return guideMapper.mapToGuideDtoList(guideRepository.findAll());
    }

    public List<GuideDto> getAvailableGuides(LocalDate date){
        return guideMapper.mapToGuideDtoList(guideRepository.findAll().stream().filter(g -> g.isAvailable())
                .collect(Collectors.toList()));
    }


}
