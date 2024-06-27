package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Cliente;
import com.grupo3.trabalhopratico.models.Requisicao;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequisicaoTest {

    private final Validator validator;

    public RequisicaoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidRequisicao() {
        Cliente cliente = new Cliente("John Doe");
        Requisicao requisicao = new Requisicao(1L, "John Doe", 4, true, Requisicao.TipoRequisicao.NA_FILA, null, cliente, null, null);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNomeClienteNotBlank() {
        Cliente cliente = new Cliente("John Doe");
        Requisicao requisicao = new Requisicao(1L, "", 4, true, Requisicao.TipoRequisicao.NA_FILA, null, cliente, null, null);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size());
        assertEquals("O nome do cliente é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testNumeroPessoasMin() {
        Cliente cliente = new Cliente("John Doe");
        Requisicao requisicao = new Requisicao(1L, "John Doe", 0, true, Requisicao.TipoRequisicao.NA_FILA, null, cliente, null, null);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size());
        assertEquals("O número de pessoas deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testClienteNotNull() {
        Requisicao requisicao = new Requisicao(1L, "John Doe", 4, true, Requisicao.TipoRequisicao.NA_FILA, null, null, null, null);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size());
        assertEquals("É obrigatório ter um cliente na requisição", violations.iterator().next().getMessage());
    }
}
