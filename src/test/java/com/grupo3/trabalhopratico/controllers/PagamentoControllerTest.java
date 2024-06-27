package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.controllers.PagamentoController;
import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.services.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPagamentos() {
        LocalDate data = LocalDate.now();
        String metodoPagamento = "Cartão";
        Pagamento pagamento = new Pagamento();
        pagamento.setValorPago(100.0);
        pagamento.setValorDescontado(10.0);
        List<Pagamento> pagamentos = Collections.singletonList(pagamento);

        when(pagamentoService.getPagamentos(data, metodoPagamento)).thenReturn(pagamentos);

        ResponseEntity<Map<String, Object>> response = pagamentoController.getPagamentos(data, metodoPagamento);
        Map<String, Object> responseBody = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<Pagamento>) responseBody.get("pagamentos")).size());
        assertEquals(100.0, responseBody.get("valorBrutoTotal"));
        assertEquals(90.0, responseBody.get("valorLiquidoTotal"));
    }

    @Test
    public void testRegisterNewPagamento() {
        Pagamento pagamento = new Pagamento();
        ResponseEntity<String> response = pagamentoController.registerNewPagamento(pagamento);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Pagamento registrado com sucesso", response.getBody());
        verify(pagamentoService, times(1)).addNewPagamento(pagamento);
    }
}
