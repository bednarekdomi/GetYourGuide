package com.App.GetYourGuide.Mapper;

import com.App.GetYourGuide.domain.Guide;
import com.App.GetYourGuide.domain.GuideDto;
import com.App.GetYourGuide.domain.OrderDetails;
import com.App.GetYourGuide.domain.OrderDetailsDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDetailsMapper {

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "guide", target = "guide")
    @Mapping(source = "isPaid", target = "isPaid")
    @Mapping(source = "isVerified", target = "isVerified")
    @Mapping(source = "isSubmitted", target = "isSubmitted")
    OrderDetails mapToOrderDetails(OrderDetailsDto orderDetailsDto);

    @InheritInverseConfiguration
    OrderDetailsDto mapToOrderDetailsDto(OrderDetails orderDetails);
}
