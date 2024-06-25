package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Mesa;
import com.grupo3.trabalhopratico.models.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository  extends JpaRepository<Mesa, long> {

    @Query("SELECT p FROM Mesa p WHERE p.id = ?1")
    List<Mesa> findMesaById(long id);

    @Query("SELECT p FROM Mesa p WHERE p.capacidade = ?1")
    Optional<Mesa> findMesaByCapacidade(int capacidade);

} 



