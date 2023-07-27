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
@NoArgsConstructor
public class OrderDetailsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToOne
    private GuideDto guideDto;
    private boolean isPaid;
    private boolean isVerified;
    private boolean isSubmitted;
}
