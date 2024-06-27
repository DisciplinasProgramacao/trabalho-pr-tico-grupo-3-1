package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
    private String nome;

    public Cliente() {}

    public Cliente(String nome) {
        setNome(nome);
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
