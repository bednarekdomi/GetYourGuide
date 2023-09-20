package com.App.GetYourGuide.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuideDto {

    private long id;
    private String name;
    private List<OrderDto> tours;
    private long daysOffSinceLastTrip;
}
