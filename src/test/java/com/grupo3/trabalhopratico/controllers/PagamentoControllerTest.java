package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.services.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
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
        LocalDate data = LocalDate.of(2023, 6, 25);
        String metodoPagamento = "cartão";
        List<Pagamento> pagamentos = Arrays.asList(
                new Pagamento(1L, 100.0, 10.0, "cartão", data),
                new Pagamento(2L, 200.0, 20.0, "cartão", data)
        );

        when(pagamentoService.getPagamentos(data, metodoPagamento)).thenReturn(pagamentos);

        ResponseEntity<Map<String, Object>> responseEntity = pagamentoController.getPagamentos(data, metodoPagamento);
        Map<String, Object> responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(300.0, responseBody.get("valorBrutoTotal"));
        assertEquals(270.0, responseBody.get("valorLiquidoTotal"));
        assertEquals(pagamentos, responseBody.get("pagamentos"));

        verify(pagamentoService, times(1)).getPagamentos(data, metodoPagamento);
    }

    @Test
    public void testRegisterNewPagamento() {
        Pagamento pagamento = new Pagamento(3L, 150.0, 15.0, "dinheiro", LocalDate.of(2023, 6, 25));

        doNothing().when(pagamentoService).addNewPagamento(pagamento);

        ResponseEntity<String> responseEntity = pagamentoController.registerNewPagamento(pagamento);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Pagamento registrado com sucesso", responseEntity.getBody());

        verify(pagamentoService, times(1)).addNewPagamento(pagamento);
    }
}
