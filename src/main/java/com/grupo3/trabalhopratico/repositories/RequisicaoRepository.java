package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {


    @Query("SELECT r FROM Requisicao r WHERE r.id = ?1")
    Optional<Requisicao> findRequisicaoById(Long id);

    @Query("SELECT r FROM Requisicao r")
    Optional<Requisicao> findRequisicao();
}
