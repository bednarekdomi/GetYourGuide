package com.App.GetYourGuide.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GuideDto {

    private long id;
    private String name;
    private List<OrderDetailsDto> tours;
    private long daysOffSinceLastTrip;
}
