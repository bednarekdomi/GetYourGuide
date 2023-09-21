package com.App.GetYourGuide.domain;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private Long customerId;
    private String name;
    private String email;
    private List<OrderDto> orderedTours;
}
