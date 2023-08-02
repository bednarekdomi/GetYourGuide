package com.App.GetYourGuide.Service;

import com.App.GetYourGuide.Mapper.GuideMapper;
import com.App.GetYourGuide.Repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;



}
