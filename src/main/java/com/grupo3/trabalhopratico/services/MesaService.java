package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Mesa;
import com.grupo3.trabalhopratico.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    @Autowired
    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @PostConstruct
    public void initializeMesas() {
        if (mesaRepository.count() == 0) {
            mesaRepository.saveAll(List.of(
                    new Mesa(4), new Mesa(4), new Mesa(4), new Mesa(4),
                    new Mesa(6), new Mesa(6), new Mesa(6), new Mesa(6),
                    new Mesa(8), new Mesa(8)
            ));
        }
    }

    public Optional<Mesa> findAvailableMesa(int numeroPessoas) {
        return mesaRepository.findAll().stream()
                .filter(m -> m.isDisponivel() && m.getCapacidade() >= numeroPessoas)
                .findFirst();
    }

    public void save(Mesa mesa) {
        mesaRepository.save(mesa);
    }
}
