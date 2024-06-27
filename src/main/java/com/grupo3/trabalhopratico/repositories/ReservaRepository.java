package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
