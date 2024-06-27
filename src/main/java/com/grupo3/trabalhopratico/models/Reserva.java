package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Entity
@Component
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidadePessoas;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Mesa> mesas;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Queue<Requisicao> filaRequisicoes;

    public Reserva() {
        this.filaRequisicoes = new LinkedList<>();
    }

    public Reserva(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
        this.filaRequisicoes = new LinkedList<>();
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    @Override
    public String toString() {
        return "Reserva para " + quantidadePessoas + " pessoas";
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void adicionarRequisicaoFila(Requisicao requisicao) {
        this.filaRequisicoes.add(requisicao);
    }

    public Requisicao removerProximaRequisicaoFila() {
        return this.filaRequisicoes.poll();
    }

    public boolean filaVazia() {
        return this.filaRequisicoes.isEmpty();
    }
}
