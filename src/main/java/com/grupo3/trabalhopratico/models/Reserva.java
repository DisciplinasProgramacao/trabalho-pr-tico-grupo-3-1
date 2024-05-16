package com.grupo3.trabalhopratico.models;
public class Reserva {
    private int quantidadePessoas;

    public Reserva(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    @Override
    public String toString() {
        return "Reserva para " + quantidadePessoas + " pessoas";
    }
}
