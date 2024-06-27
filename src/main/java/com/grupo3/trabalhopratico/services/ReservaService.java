package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.models.Reserva;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final Reserva reserva;

    public ReservaService() {
        this.reserva = new Reserva(0);
    }

    public void addRequisicaoToFila(Requisicao requisicao) {
        reserva.adicionarRequisicaoFila(requisicao);
    }

    public boolean isFilaVazia() {
        return reserva.filaVazia();
    }

    public Requisicao removerProximaRequisicaoFila() {
        return reserva.removerProximaRequisicaoFila();
    }
}
