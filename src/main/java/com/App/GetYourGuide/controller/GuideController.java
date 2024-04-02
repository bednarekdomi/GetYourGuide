package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/guide")
public class GuideController {

    private final GuideService guideService;

    @GetMapping("/getAvailableGuides")
    public List<Guide> getAvailableGuides(@RequestParam LocalDate date) {
        return guideService.getAvailableGuides(date);
    }

    @GetMapping("/getDaysOffSinceLastTrip")
    public long getDaysOffSinceLastTrip(@RequestParam long guideId) {
        return guideService.getDaysOffSinceLastTrip(guideId);
    }
}
