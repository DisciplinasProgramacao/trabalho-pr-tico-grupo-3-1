package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPagamentos(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data,
            @RequestParam(required = false) String metodoPagamento) {
        List<Pagamento> pagamentos = pagamentoService.getPagamentos(data, metodoPagamento);
        double valorBrutoTotal = pagamentos.stream().mapToDouble(Pagamento::getValorPago).sum();
        double valorLiquidoTotal = pagamentos.stream().mapToDouble(p -> p.getValorPago() - p.getValorDescontado()).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("valorBrutoTotal", valorBrutoTotal);
        response.put("valorLiquidoTotal", valorLiquidoTotal);
        response.put("pagamentos", pagamentos);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> registerNewPagamento(@Valid @RequestBody Pagamento pagamento) {
        pagamentoService.addNewPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pagamento registrado com sucesso");
    }
}
