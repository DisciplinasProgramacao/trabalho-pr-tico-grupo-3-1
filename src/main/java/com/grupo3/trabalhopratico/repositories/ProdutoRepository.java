package com.grupo3.trabalhopratico.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    @Query("SELECT p FROM Produto p WHERE p.nome = ?1")
    Optional<Produto> findProdutoByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.preco = ?1")
    Optional<Produto> findProdutoByPreco(double preco);
}
