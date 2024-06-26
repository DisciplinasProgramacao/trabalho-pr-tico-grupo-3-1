package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> getPagamentos(LocalDate data, String metodoPagamento) {
        if (data != null && metodoPagamento != null) {
            return pagamentoRepository.findByDataPagamentoAndMetodoPagamento(data, metodoPagamento);
        } else if (data != null) {
            return pagamentoRepository.findByDataPagamento(data);
        } else if (metodoPagamento != null) {
            return pagamentoRepository.findByMetodoPagamento(metodoPagamento);
        } else {
            return pagamentoRepository.findAll();
        }
    }

    public void addNewPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }
}
