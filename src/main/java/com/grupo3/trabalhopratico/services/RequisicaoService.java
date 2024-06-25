package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.exceptions.PreconditionFailedException;
import com.grupo3.trabalhopratico.exceptions.EntityAlreadyClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;

    @Autowired
    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    public List<Requisicao> getRequisicoesAtivas() {
        return requisicaoRepository.findByAtiva(true);
    }

    public List<Requisicao> getRequisicoesByTipo(String tipo) {
        if (tipo.equalsIgnoreCase("EM_ATENDIMENTO")) {
            return requisicaoRepository.findByEmAtendimento(true);
        } else if (tipo.equalsIgnoreCase("NA_FILA")) {
            return requisicaoRepository.findByEmAtendimento(false);
        } else {
            throw new IllegalArgumentException("Tipo de requisição inválido.");
        }
    }

    public void addNewRequisicao(Requisicao requisicao) {
        requisicao.setAtiva(true);
        requisicao.setEmAtendimento(false);
        requisicaoRepository.save(requisicao);
    }

    public Requisicao getRequisicao(Long requisicaoId) {
        return requisicaoRepository.findById(requisicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada."));
    }

    public void addProdutosToRequisicao(Long requisicaoId, List<Produto> produtos) {
        Requisicao requisicao = requisicaoRepository.findById(requisicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada."));

        if (!requisicao.isEmAtendimento() || !requisicao.isAtiva()) {
            throw new PreconditionFailedException("Requisição não está em atendimento ou já foi finalizada.");
        }

        requisicao.getProdutos().addAll(produtos);
        requisicaoRepository.save(requisicao);
    }

    public void closeRequisicao(Long requisicaoId) {
        Requisicao requisicao = requisicaoRepository.findById(requisicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada."));

        if (!requisicao.isAtiva()) {
            throw new EntityAlreadyClosedException("Requisição já está encerrada.");
        }

        if (!requisicao.isEmAtendimento()) {
            throw new PreconditionFailedException("Requisição ainda está na fila.");
        }

        requisicao.setAtiva(false);
        requisicaoRepository.save(requisicao);
    }
}
