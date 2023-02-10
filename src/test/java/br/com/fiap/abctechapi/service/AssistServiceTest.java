package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.service.impl.AssistServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AssistServiceTest {

    @Mock
    private AssistRepository assistRepository;
    private AssistService assistService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        assistService = new AssistServiceImpl(assistRepository);
    }

    @DisplayName("Teste lista com sucesso com 2 itens")
    @Test
    void list_success() {
        Assist assist = new Assist(1L, "Test", "Description Test");
        Assist assist2 = new Assist(2L, "Test 2", "Description Test 2");

        when(assistRepository.findAll()).thenReturn(List.of(assist, assist2));

        List<Assist> values = assistService.getServiceList();

        Assertions.assertEquals(values.size(), 2);
        Assertions.assertEquals(values.get(0).getId(), assist.getId());
        Assertions.assertEquals(values.get(1).getId(), assist2.getId());
    }

    @DisplayName("Teste lista vazia")
    @Test
    void empty_list() {
        when(assistRepository.findAll()).thenReturn(List.of());

        List<Assist> values = assistService.getServiceList();

        Assertions.assertEquals(values.size(), 0);
    }
}