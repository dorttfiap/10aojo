package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistsException;
import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private AssistRepository assistRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository, @Autowired AssistRepository assistRepository) {
        this.orderRepository = orderRepository;
        this.assistRepository = assistRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> arrayAssists) throws Exception {
        ArrayList<Assist> assists = new ArrayList<>();

        arrayAssists.forEach(i -> {
            Assist assist = assistRepository.findById(i).orElseThrow();
            assists.add(assist);
        });

        order.setServices(assists);

        if (!order.hasMinAssists()) {
            throw new MinimumAssistsException("Invalid assists", "Adicione ao menos uma assistência.");
        } else if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException("Invalid Assists", "Número máximo de assistências excedido.");
        }

        orderRepository.save(order);
    }
}
