package com.grupo3.trabalhopratico.models;
import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int capacidade;
    private List<Reserva> reservas;

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
        reservas = new ArrayList<>();
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean estaDisponivel(Reserva novaReserva) {
        return capacidade >= novaReserva.getQuantidadePessoas() && reservas.isEmpty();
    }

    public void alocarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public boolean concluirReserva(int quantidadePessoas) {
        for (Reserva reserva : reservas) {
            if (reserva.getQuantidadePessoas() == quantidadePessoas) {
                reservas.remove(reserva);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Capacidade: " + capacidade + ", Reservas: " + reservas.size();
    }
}
