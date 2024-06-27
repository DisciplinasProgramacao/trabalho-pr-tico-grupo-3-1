package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    @Query("SELECT r FROM Requisicao r WHERE r.ativa = true")
    List<Requisicao> findRequisicoesAtivas();

    @Query("SELECT r FROM Requisicao r WHERE r.tipo = 'EM_ATENDIMENTO'")
    List<Requisicao> findRequisicoesEmAtendimento();

    @Query("SELECT r FROM Requisicao r WHERE r.tipo = 'NA_FILA'")
    List<Requisicao> findRequisicoesNaFila();

    boolean existsByClienteAndAtiva(Cliente cliente, boolean ativa);
}
