package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.exceptions.DuplicateEntityException;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.getProdutos();
    }

    @PostMapping
    public ResponseEntity<String> registerNewProduto(@Valid @RequestBody Produto produto) {
        try {
            produtoService.addNewProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{produtoId}")
    public ResponseEntity<String> deleteProduto(@PathVariable("produtoId") Long produtoId) {
        try {
            produtoService.deleteProduto(produtoId);
            return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<String> updateProduto(
            @PathVariable Long produtoId,
            @Valid @RequestBody Produto produtoDetails) {
        try {
            produtoService.updateProduto(produtoId, produtoDetails.getNome(), produtoDetails.getPreco());
            return ResponseEntity.ok("Produto atualizado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o produto.");
        }
    }
}
