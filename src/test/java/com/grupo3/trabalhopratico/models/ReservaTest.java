package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Reserva;
import com.grupo3.trabalhopratico.models.Requisicao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservaTest {

    @Test
    public void testAdicionarRequisicaoFila() {
        Reserva reserva = new Reserva(4);
        Requisicao requisicao = new Requisicao();

        reserva.adicionarRequisicaoFila(requisicao);

        assertEquals(1, reserva.getFilaRequisicoes().size());
        assertEquals(requisicao, reserva.getFilaRequisicoes().peek());
    }

    @Test
    public void testRemoverProximaRequisicaoFila() {
        Reserva reserva = new Reserva(4);
        Requisicao requisicao = new Requisicao();

        reserva.adicionarRequisicaoFila(requisicao);
        Requisicao removida = reserva.removerProximaRequisicaoFila();

        assertEquals(requisicao, removida);
        assertTrue(reserva.getFilaRequisicoes().isEmpty());
    }
}
