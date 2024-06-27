package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.models.Reserva;
import com.grupo3.trabalhopratico.services.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRequisicaoToFila() {
        Requisicao requisicao = new Requisicao();
        reservaService.addRequisicaoToFila(requisicao);
        verify(reserva, times(1)).adicionarRequisicaoFila(requisicao);
    }

    @Test
    public void testIsFilaVazia() {
        when(reserva.filaVazia()).thenReturn(true);
        assertTrue(reservaService.isFilaVazia());

        when(reserva.filaVazia()).thenReturn(false);
        assertFalse(reservaService.isFilaVazia());
    }

    @Test
    public void testRemoverProximaRequisicaoFila() {
        Requisicao requisicao = new Requisicao();
        when(reserva.removerProximaRequisicaoFila()).thenReturn(requisicao);

        Requisicao proximaRequisicao = reservaService.removerProximaRequisicaoFila();
        assertEquals(requisicao, proximaRequisicao);
    }
}
