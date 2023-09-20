package com.App.GetYourGuide.service;

import com.App.GetYourGuide.domain.*;
import com.App.GetYourGuide.mapper.OrderMapper;
import com.App.GetYourGuide.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderRepository orderRepository;

    private OrderDto orderOneDto;
    private Order orderOne;

    @BeforeEach
    public void setUp(){
        TourOrder firstOrderDecorator = new InsuranceDecorator(new EquipmentDecorator(new BasicOrderDecorator()));
        orderOne = new Order(1L, firstOrderDecorator, new Guide(), LocalDate.of(2023, 9, 30), true, true, true,
                new Customer(), firstOrderDecorator.getDescription(), firstOrderDecorator.getCost());
        orderOneDto = new OrderDto(1L, firstOrderDecorator, new GuideDto(), LocalDate.of(2023, 9, 30), true,
                true, true, new CustomerDto(), firstOrderDecorator.getDescription(), firstOrderDecorator.getCost());
    }

    @Test
    void shouldGetOrderById() {
        //Given
        when(orderMapper.mapToOrderDetailsDto(orderOne)).thenReturn(orderOneDto);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderOne));
        //When
        Optional<OrderDto> order = orderService.getOrder(1L);
        //Then
        assertTrue(order.isPresent());
        assertEquals(orderOneDto, order.get());
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void createOrder() {
    }

    @Test
    void updateOrderDetails() {
    }

    @Test
    void refundPayment() {
    }
}