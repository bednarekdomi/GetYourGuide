package com.App.GetYourGuide.controller;

import com.App.GetYourGuide.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/getYourGuide/guide")
public class GuideController {

    private final GuideService guideService;
}
