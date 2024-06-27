package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Requisicao {

    public enum TipoRequisicao {
        EM_ATENDIMENTO,
        NA_FILA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do cliente deve ter entre 3 e 50 caracteres")
    private String nomeCliente;

    @Min(value = 1, message = "O número de pessoas deve ser maior que 0")
    @Max(value = 10, message = "O número de pessoas deve ser menor ou igual a 10")
    private int numeroPessoas;

    private boolean ativa;

    @Enumerated(EnumType.STRING)
    private TipoRequisicao tipo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "É obrigatório ter um cliente na requisição")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    private boolean emAtendimento;

    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public Requisicao() {}

    public Requisicao(Long id, String nomeCliente, int numeroPessoas, boolean ativa, TipoRequisicao tipo, List<Produto> produtos, Cliente cliente, Mesa mesa, LocalDateTime horaEntrada) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.numeroPessoas = numeroPessoas;
        this.ativa = ativa;
        this.tipo = tipo;
        this.produtos = produtos;
        this.cliente = cliente;
        this.mesa = mesa;
        this.emAtendimento = false;
        this.horaEntrada = horaEntrada;
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

    public TipoRequisicao getTipo() {
        return tipo;
    }

    public void setTipo(TipoRequisicao tipo) {
        this.tipo = tipo;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }
}
