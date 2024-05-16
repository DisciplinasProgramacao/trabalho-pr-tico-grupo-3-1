package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {
    private final RequisicaoRepository requisicaoRepository;

    @Autowired
    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    public List<Requisicao> getRequisicoes(){
        return requisicaoRepository.findAll();
    }

    public void addNewRequisicao(Requisicao requisicao) {
        Optional<Requisicao> requisicaoOptional = requisicaoRepository.findRequisicaoById(requisicao.getId());
        if(requisicaoOptional.isPresent()){
            throw new IllegalStateException("Requisição ja existe!!!");
        }
        requisicaoRepository.save(requisicao);
    }

    public void deleteRequisicao(Long requisicaoId) {
        boolean exists = requisicaoRepository.existsById(requisicaoId);
        if(!exists) {
            throw new IllegalStateException("A requisição com id " + requisicaoId + " não existe.");
        }
        requisicaoRepository.deleteById(requisicaoId);
    }
}
