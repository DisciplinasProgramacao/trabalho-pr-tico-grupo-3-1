package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
