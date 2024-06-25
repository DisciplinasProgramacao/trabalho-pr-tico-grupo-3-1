package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByDataPagamento(LocalDate dataPagamento);

    List<Pagamento> findByMetodoPagamento(String metodoPagamento);

    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento = ?1 AND p.metodoPagamento = ?2")
    List<Pagamento> findByDataPagamentoAndMetodoPagamento(LocalDate dataPagamento, String metodoPagamento);
}
