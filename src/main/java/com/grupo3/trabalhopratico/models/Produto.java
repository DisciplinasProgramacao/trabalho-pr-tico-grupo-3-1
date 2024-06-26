package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Produto {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    public Long id;
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    @Min(value = 0, message = "O preço deve ser maior que zero")
    private double preco;
    @NotBlank(message = "O tipo do produto (bebida ou comida) é obrigatório")
    private String tipo;
    @Size(min = 5, max = 25, message = "A descrição deve ter entre 5 e 25 caracteres")
    private String descricao;

    public Produto() {
    }

    public Produto(Long id, String nome, double preco, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Produto(String nome, double preco, String tipo) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao +
                '}';
    }
}
