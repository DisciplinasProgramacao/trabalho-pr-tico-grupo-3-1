package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<Pagamento> getPagamento(){
        return pagamentoService.getPagamentos();
    }

    @PostMapping
    public void registerNewPagamento(@RequestBody Pagamento pagamento){
        pagamentoService.addNewPagamento(pagamento);
    }

}