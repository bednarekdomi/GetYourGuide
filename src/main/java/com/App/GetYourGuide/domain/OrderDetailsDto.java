package com.App.GetYourGuide.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailsDto {

    private Long orderId;
    private GuideDto guideDto;
    private boolean isPaid;
    private boolean isVerified;
    private boolean isSubmitted;
}
