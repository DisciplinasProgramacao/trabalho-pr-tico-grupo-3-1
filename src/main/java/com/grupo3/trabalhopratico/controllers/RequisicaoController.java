package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {
    private final RequisicaoService requisicaoService;

    @Autowired
    public RequisicaoController(RequisicaoService requisicaoService) {
        this.requisicaoService = requisicaoService;
    }

    @GetMapping
    public List<Requisicao> getRequisicoes(@RequestParam Optional<String> tipo) {
        if (tipo.isPresent()) {
            return requisicaoService.getRequisicoesByTipo(tipo.get());
        } else {
            return requisicaoService.getRequisicoesAtivas();
        }
    }

    @PostMapping
    public ResponseEntity<String> registerNewRequisicao(@Valid @RequestBody Requisicao requisicao) {
        requisicaoService.addNewRequisicao(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Requisição criada com sucesso");
    }

    @GetMapping(path = "{idRequisicao}")
    public ResponseEntity<Requisicao> getRequisicao(@PathVariable("idRequisicao") Long requisicaoId) {
        Requisicao requisicao = requisicaoService.getRequisicao(requisicaoId);
        return ResponseEntity.ok(requisicao);
    }

    @PostMapping(path = "{idRequisicao}/produtos")
    public ResponseEntity<String> addProdutosToRequisicao(@PathVariable("idRequisicao") Long requisicaoId, @RequestBody List<Produto> produtos) {
        requisicaoService.addProdutosToRequisicao(requisicaoId, produtos);
        return ResponseEntity.status(HttpStatus.OK).body("Produtos adicionados à requisição com sucesso");
    }

    @DeleteMapping(path = "{idRequisicao}")
    public ResponseEntity<String> deleteRequisicao(@PathVariable("idRequisicao") Long requisicaoId) {
        requisicaoService.closeRequisicao(requisicaoId);
        return ResponseEntity.status(HttpStatus.OK).body("Requisição encerrada e mesa liberada com sucesso");
    }
}
