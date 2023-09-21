package com.App.GetYourGuide.mapper;

import com.App.GetYourGuide.domain.Order;
import com.App.GetYourGuide.domain.OrderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(uses = {GuideMapper.class, CustomerMapper.class}, componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source="tour", target = "tour")
    @Mapping(source = "guideDto", target = "guide")
    @Mapping(source = "isPaid", target = "isPaid")
    @Mapping(source = "isVerified", target = "isVerified")
    @Mapping(source = "isSubmitted", target = "isSubmitted")
    @Mapping(source="customerDto", target = "customer")
    Order mapToOrderDetails(OrderDto orderDto);

    @InheritInverseConfiguration
    OrderDto mapToOrderDetailsDto(Order order);

    @IterableMapping(elementTargetType = OrderDto.class)
    List<OrderDto>mapToOrderDetailsDtoList(List<Order> Orders);

    default Optional<OrderDto> mapToOrderDetailsDto(Optional<Order> orderDetails){
        return orderDetails.map(this::mapToOrderDetailsDto);
    }
}
