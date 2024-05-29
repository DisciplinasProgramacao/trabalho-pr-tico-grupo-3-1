package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {


    @Query("SELECT s FROM Pagamento s WHERE s.metodoPagamento = ?1")
    Optional<Pagamento> findPagamentoByMetodo(String metodoPagamento);

    @Query("SELECT s FROM Pagamento s WHERE s.dataPagamento = ?1")
    Optional<Pagamento> findPagamentoByData(int dataPagamento);
}

