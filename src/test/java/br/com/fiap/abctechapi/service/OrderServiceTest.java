package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.CoordinatesException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistsException;
import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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

    private void setValidLocations (Order order) {
        OrderLocation startOrderLocation = new OrderLocation();
        startOrderLocation.setLatitude(0.0);
        startOrderLocation.setLongitude(0.0);

        order.setStartOrderLocation(startOrderLocation);

        OrderLocation endOrderLocation = new OrderLocation();
        endOrderLocation.setLatitude(0.0);
        endOrderLocation.setLongitude(0.0);

        order.setEndOrderLocation(endOrderLocation);
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

        setValidLocations(newOrder);

        orderService.saveOrder(newOrder, List.of(1L));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    @DisplayName("Criação de Ordem mínima (1)")
    public void create_order_error_minimum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MinimumAssistsException.class, () -> orderService.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    @DisplayName("Criação de Ordem máxima (0)")
    public void create_order_error_maximum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, List.of(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    @DisplayName("Localização de inicio da assistencia inválida")
    public void create_order_invalid_start_location() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);
        setValidLocations(newOrder);

        newOrder.getStartOrderLocation().setLatitude(300.0);
        newOrder.getEndOrderLocation().setLatitude(newOrder.getStartOrderLocation().getLatitude());
        newOrder.getEndOrderLocation().setLongitude(newOrder.getStartOrderLocation().getLongitude());

        Assertions.assertThrows(CoordinatesException.class, () -> orderService.saveOrder(newOrder, List.of(1L)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    @DisplayName("Distância minima de entrada e saida")
    public void create_order_invalid_distance_locations() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);
        setValidLocations(newOrder);

        OrderLocation startOrderLocation = newOrder.getStartOrderLocation();
        OrderLocation endOrderLocation = newOrder.getEndOrderLocation();

        // São Paulo: Bixiga (-23.5575305501258, -46.645470388852964)
        startOrderLocation.setLatitude(-23.5575305501258);
        startOrderLocation.setLongitude(-46.645470388852964);

        // São Paulo: Liberdade (-23.559884183086325, -46.63145170822044)
        endOrderLocation.setLatitude(-23.559884183086325);
        endOrderLocation.setLongitude(-46.63145170822044);

        // Distancia: ~1.45km (limite: 10 metros)

        Assertions.assertThrows(CoordinatesException.class, () -> orderService.saveOrder(newOrder, List.of(1L)));
        verify(orderRepository, times(0)).save(newOrder);
    }
}