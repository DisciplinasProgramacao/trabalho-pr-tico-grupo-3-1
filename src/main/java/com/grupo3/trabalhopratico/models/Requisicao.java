package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;

@Entity
@Table
public class Requisicao {
    @Id
    @SequenceGenerator(
            name = "requisicao_sequence",
            sequenceName = "requisicao_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "requisicao_sequence"
    )
    private Long id;
    private String tipo;
   

    public Requisicao(int i, int j, int k, int l) {
    }

    public Requisicao(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
