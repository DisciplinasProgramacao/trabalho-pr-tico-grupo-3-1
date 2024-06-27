package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.repositories.PagamentoRepository;
import com.grupo3.trabalhopratico.services.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPagamentos() {
        LocalDate data = LocalDate.now();
        String metodoPagamento = "Cartão";
        Pagamento pagamento = new Pagamento(1L, 100.0, 10.0, metodoPagamento, data);

        when(pagamentoRepository.findByDataPagamentoAndMetodoPagamento(data, metodoPagamento))
                .thenReturn(List.of(pagamento));

        List<Pagamento> pagamentos = pagamentoService.getPagamentos(data, metodoPagamento);
        assertEquals(1, pagamentos.size());
        assertEquals(pagamento, pagamentos.get(0));
    }

    @Test
    public void testAddNewPagamento() {
        Pagamento pagamento = new Pagamento();
        pagamentoService.addNewPagamento(pagamento);
        verify(pagamentoRepository, times(1)).save(pagamento);
    }
}
