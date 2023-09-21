package com.App.GetYourGuide.mapper;

import com.App.GetYourGuide.domain.Customer;
import com.App.GetYourGuide.domain.CustomerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "name")
    @Mapping(source = "orderedTours", target = "orderedTours")
    Customer mapToCustomer(CustomerDto customerDto);

    @InheritInverseConfiguration
    CustomerDto mapToCustomerDto(Customer customer);

    default Optional<CustomerDto> mapToCustomerDto(Optional<Customer> customer){
        return customer.map(this::mapToCustomerDto);
    }

}
