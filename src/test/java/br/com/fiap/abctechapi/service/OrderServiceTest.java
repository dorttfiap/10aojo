package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AssistRepository assistRepository;

    private OrderService orderService;

    @BeforeEach()
    public void init() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository, assistRepository);

        when(assistRepository.findById(any())).thenReturn(Optional.of(new Assist(1L, "Teste", "Description")));
    }

    @Test
    @DisplayName("Serviços não nulo")
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }

    @Test
    @DisplayName("Criação de Ordem com sucesso")
    public void create_order_success() throws Exception {
        Order newOrder = new Order();

        newOrder.setOperatorId(1234L);

        orderService.saveOrder(newOrder, List.of(1L));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    @DisplayName("Criação de Ordem mínima (1)")
    public void create_order_error_minimum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(Exception.class, () -> orderService.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    @DisplayName("Criação de Ordem máxima (0)")
    public void create_order_error_maximum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(Exception.class, () -> orderService.saveOrder(newOrder, List.of(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L)), "Expected doThing() to throw, but it didn't");
        verify(orderRepository, times(0)).save(newOrder);
    }
}