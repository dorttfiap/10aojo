package br.com.fiap.abctechapi.application.impl;


import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderService orderService;

    @Autowired
    public OrderApplicationImpl (OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createOrder(OrderDto orderDto) throws Exception {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(this.getOrderLocationFromDto(orderDto.getStart()));
        order.setEndOrderLocation(this.getOrderLocationFromDto(orderDto.getEnd()));
        this.orderService.saveOrder(order, orderDto.getAssists());
    }

    private OrderLocation getOrderLocationFromDto(OrderLocationDto orderLocationDto) {
        OrderLocation orderLocation = new OrderLocation();
        orderLocation.setLatitude(orderLocationDto.getLatitude());
        orderLocation.setLongitude(orderLocationDto.getLongitude());
        orderLocation.setDate(orderLocationDto.getDateTime());
        return orderLocation;
    }
}
