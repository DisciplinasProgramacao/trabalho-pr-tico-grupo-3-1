package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Reserva;
import jakarta.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacidade;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public Mesa() {}

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
        this.status = false; // Inicialmente disponível
    }

    public Mesa(int id, int capacidade) {
        this.id = (long) id;
        this.capacidade = capacidade;
        this.status = false; // Inicialmente disponível
    }

    public Long getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isDisponivel() {
        return !status; // Se status é false, está disponível
    }

    public void setDisponivel(boolean disponivel) {
        this.status = !disponivel; // Se disponivel é true, status deve ser false
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Id Mesa: " + id + ", Capacidade: " + capacidade;
    }
}
