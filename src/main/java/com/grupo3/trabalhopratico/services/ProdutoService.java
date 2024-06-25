package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.repositories.ProdutoRepository;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public void addNewProduto(Produto produto) {
        if (produtoRepository.findProdutoByNome(produto.getNome()).isPresent()) {
            throw new DuplicateEntityException("Já existe um produto com este nome.");
        }
        produtoRepository.save(produto);
    }

    public void deleteProduto(Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new EntityNotFoundException("Produto não encontrado.");
        }
        produtoRepository.deleteById(produtoId);
    }

    public void updateProduto(Long produtoId, String nome, double preco) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

        if (nome != null && !nome.isEmpty()) {
            produto.setNome(nome);
        }
        if (preco > 0) {
            produto.setPreco(preco);
        }
        produtoRepository.save(produto);
    }
}
