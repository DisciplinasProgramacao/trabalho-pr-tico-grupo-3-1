package com.grupo3.trabalhopratico.repositories;

import com.grupo3.trabalhopratico.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByDataPagamento(LocalDate dataPagamento);

    List<Pagamento> findByMetodoPagamento(String metodoPagamento);

    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento = :dataPagamento AND p.metodoPagamento = :metodoPagamento ORDER BY p.dataPagamento")
    List<Pagamento> findByDataPagamentoAndMetodoPagamento(
            @Param("dataPagamento") LocalDate dataPagamento,
            @Param("metodoPagamento") String metodoPagamento);

    @Query("SELECT SUM(p.valorPago) FROM Pagamento p")
    Double sumValorBrutoTotal();

    @Query("SELECT SUM(p.valorPago - p.valorDescontado) FROM Pagamento p")
    Double sumValorLiquidoTotal();

    @Query("SELECT p FROM Pagamento p ORDER BY p.dataPagamento")
    List<Pagamento> findAllOrderByDataPagamento();
}
