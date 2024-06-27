package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.models.Mesa;
import com.grupo3.trabalhopratico.repositories.MesaRepository;
import com.grupo3.trabalhopratico.services.MesaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MesaServiceTest {

    @Mock
    private MesaRepository mesaRepository;

    @InjectMocks
    private MesaService mesaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitializeMesas() {
        when(mesaRepository.count()).thenReturn(0L);
        mesaService.initializeMesas();
        verify(mesaRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    public void testFindAvailableMesa() {
        Mesa mesa = new Mesa(4);
        mesa.setDisponivel(true);
        when(mesaRepository.findAll()).thenReturn(List.of(mesa));

        Optional<Mesa> availableMesa = mesaService.findAvailableMesa(4);
        assertTrue(availableMesa.isPresent());
        assertEquals(mesa, availableMesa.get());
    }

    @Test
    public void testSave() {
        Mesa mesa = new Mesa(4);
        mesaService.save(mesa);
        verify(mesaRepository, times(1)).save(mesa);
    }
}
