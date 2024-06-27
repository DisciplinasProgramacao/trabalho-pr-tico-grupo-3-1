package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.*;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.exceptions.EntityAlreadyClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;
    private final MesaService mesaService;
    private final ReservaService reservaService;

    @Autowired
    public RequisicaoService(RequisicaoRepository requisicaoRepository, MesaService mesaService, ReservaService reservaService) {
        this.requisicaoRepository = requisicaoRepository;
        this.mesaService = mesaService;
        this.reservaService = reservaService;
    }

    public List<Requisicao> getRequisicoesAtivas() {
        return requisicaoRepository.findRequisicoesAtivas();
    }

    public List<Requisicao> getRequisicoesByTipo(String tipo) {
        if (tipo.equalsIgnoreCase("EM_ATENDIMENTO")) {
            return requisicaoRepository.findRequisicoesEmAtendimento();
        } else if (tipo.equalsIgnoreCase("NA_FILA")) {
            return requisicaoRepository.findRequisicoesNaFila();
        } else {
            throw new IllegalArgumentException("Tipo de requisição inválido.");
        }
    }

    @Transactional
    public void addNewRequisicao(Requisicao requisicao) {
        requisicao.setAtiva(true);
        requisicao.setEmAtendimento(false);
        requisicao.setHoraEntrada(LocalDateTime.now());

        Optional<Mesa> mesaOptional = mesaService.findAvailableMesa(requisicao.getNumeroPessoas());

        if (mesaOptional.isPresent()) {
            Mesa mesa = mesaOptional.get();
            mesa.setDisponivel(false);
            requisicao.setMesa(mesa);
            requisicao.setEmAtendimento(true);
            requisicao.setTipo(Requisicao.TipoRequisicao.EM_ATENDIMENTO);
        } else {
            requisicao.setTipo(Requisicao.TipoRequisicao.NA_FILA);
            reservaService.addRequisicaoToFila(requisicao);
        }

        requisicaoRepository.save(requisicao);
    }

    public Requisicao getRequisicao(Long requisicaoId) {
        return requisicaoRepository.findById(requisicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada."));
    }

    public void addProdutosToRequisicao(Long requisicaoId, List<Produto> produtos) {
        Requisicao requisicao = getRequisicao(requisicaoId);
        requisicao.getProdutos().addAll(produtos);
        requisicaoRepository.save(requisicao);
    }

    @Transactional
    public void setRequisicaoEmAtendimento(Long requisicaoId) {
        Requisicao requisicao = getRequisicao(requisicaoId);
        requisicao.setEmAtendimento(true);
        requisicaoRepository.save(requisicao);
    }

    @Transactional
    public void closeRequisicao(Long requisicaoId) {
        Requisicao requisicao = getRequisicao(requisicaoId);
        if (!requisicao.isAtiva()) {
            throw new EntityAlreadyClosedException("A requisição já está encerrada.");
        }
        requisicao.setAtiva(false);
        requisicao.setHoraSaida(LocalDateTime.now());

        if (requisicao.getMesa() != null) {
            requisicao.getMesa().setDisponivel(true);
        }

        if (!reservaService.isFilaVazia()) {
            Requisicao nextRequisicao = reservaService.removerProximaRequisicaoFila();
            Mesa mesa = requisicao.getMesa();
            if (mesa != null) {
                mesa.setDisponivel(false);
                nextRequisicao.setMesa(mesa);
                nextRequisicao.setEmAtendimento(true);
                nextRequisicao.setTipo(Requisicao.TipoRequisicao.EM_ATENDIMENTO);
            }
            requisicaoRepository.save(nextRequisicao);
        }

        requisicaoRepository.save(requisicao);
    }
}
