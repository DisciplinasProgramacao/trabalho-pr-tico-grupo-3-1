package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }

    public void addNewProduto(Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findProdutoByNome(produto.getNome());
        if(produtoOptional.isPresent()){
            throw new IllegalStateException("Produto ja cadastrado!!!");
        }
        produtoRepository.save(produto);
    }

    public void deleteProduto(Long produtoId) {
        boolean exists = produtoRepository.existsById(produtoId);
        if(!exists) {
            throw new IllegalStateException("O produto com id " + produtoId + " não existe.");
        }
        produtoRepository.deleteById(produtoId);
    }

    @Transactional
    public void updateProduto(Long produtoId,
                              String nome,
                              double preco) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new IllegalStateException("O produto com id " + produtoId + " não existe."));
        if (nome != null && !nome.isEmpty() && !Objects.equals(produto.getNome(), nome)) {
            Optional<Produto> produtoOptional = produtoRepository.findProdutoByNome(produto.getNome());
            if(produtoOptional.isPresent()){
                throw new IllegalStateException("Produto ja cadastrado!!!");
            }
            produto.setNome(nome);
        }

        if (preco > 0) {
            produto.setPreco(preco);
        }
    }
}
