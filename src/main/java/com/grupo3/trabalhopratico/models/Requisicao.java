package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
    private String nomeCliente;

    @Min(value = 1, message = "O número de pessoas deve ser maior que 0")
    @Max(value = 10, message = "O número de pessoas deve ser menor ou igual a 10") // Supondo que a capacidade máxima seja 10
    private int numeroPessoas;

    private boolean ativa;
    private boolean emAtendimento;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @NotNull(message = "É obrigatório ter um cliente na requisição")
    @Embedded
    private Cliente cliente;

    public Requisicao(Long id, String nomeCliente, int numeroPessoas, boolean ativa, boolean emAtendimento, List<Produto> produtos, Cliente cliente) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.numeroPessoas = numeroPessoas;
        this.ativa = ativa;
        this.emAtendimento = emAtendimento;
        this.produtos = produtos;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
