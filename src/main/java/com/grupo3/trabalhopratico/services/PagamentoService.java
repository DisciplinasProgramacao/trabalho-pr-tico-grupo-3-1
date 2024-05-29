package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> getPagamentos(){
        return pagamentoRepository.findAll();
    }

    public void addNewPagamento(Pagamento pagamento) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findPagamentoByMetodo(pagamento.getMetodoPagamento());
        if(pagamentoOptional.isPresent()){
            throw new IllegalStateException("Pagamento ja existe!!!");
        }
        pagamentoRepository.save(pagamento);
    }
}

