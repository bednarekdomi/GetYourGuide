package com.App.GetYourGuide.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private GuideDto guideDto;
    private boolean isPaid;
    private boolean isVerified;
    private boolean isSubmitted;
    private CustomerDto customerDto;
}
