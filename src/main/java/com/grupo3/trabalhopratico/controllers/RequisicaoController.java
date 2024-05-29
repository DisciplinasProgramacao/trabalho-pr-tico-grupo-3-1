package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {
    private final RequisicaoService requisicaoService;

    @Autowired
    public RequisicaoController(RequisicaoService requisicaoService) {
        this.requisicaoService = requisicaoService;
    }

    @GetMapping
    public List<Requisicao> getRequisicoes(){
        return requisicaoService.getRequisicoes();
    }

    @PostMapping
    public void registerNewRequisicao(@RequestBody Requisicao requisicao){
        requisicaoService.addNewRequisicao(requisicao);
    }

    @DeleteMapping(path = "{idRequisicao}")
    public void deleteRequisicao(@PathVariable("idRequisicao") Long requisicaoId){
        requisicaoService.deleteRequisicao(requisicaoId);
    }
}

