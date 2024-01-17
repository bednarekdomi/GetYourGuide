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

    private long Id;
    private String name;
    private String email;
    private List<OrderDto> tours;

}
