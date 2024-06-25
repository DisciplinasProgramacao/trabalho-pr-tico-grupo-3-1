package com.grupo3.trabalhopratico.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cliente {

    private int idCliente;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
    private String nome;

    private static int contadorId = 1;

    public Cliente(String nome) {
        setNome(nome);
        this.idCliente = contadorId++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
