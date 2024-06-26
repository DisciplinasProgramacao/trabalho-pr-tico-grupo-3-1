package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    @Query("SELECT r FROM Requisicao r WHERE r.ativa = true")
    List<Requisicao> findRequisicoesAtivas();

    @Query("SELECT r FROM Requisicao r WHERE r.emAtendimento = true")
    List<Requisicao> findRequisicoesEmAtendimento();

    @Query("SELECT r FROM Requisicao r WHERE r.emAtendimento = false AND r.ativa = true")
    List<Requisicao> findRequisicoesNaFila();

    @Query("SELECT r FROM Requisicao r WHERE r.emAtendimento = false AND r.ativa = true ORDER BY r.id ASC")
    Optional<Requisicao> findNextRequisicaoNaFila();

    @Query("SELECT r FROM Requisicao r WHERE r.emAtendimento = true AND r.ativa = true ORDER BY r.id ASC")
    List<Requisicao> findByEmAtendimento(boolean b);

    @Query("SELECT r FROM Requisicao r WHERE r.ativa = true")
    List<Requisicao> findByAtiva(boolean b);
}
