package com.App.GetYourGuide.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private TourOrder tour;
    private GuideDto guideDto;
    private LocalDate tourDate;
    private boolean isPaid;
    private boolean isVerified;
    private boolean isSubmitted;
    private CustomerDto customerDto;
}
