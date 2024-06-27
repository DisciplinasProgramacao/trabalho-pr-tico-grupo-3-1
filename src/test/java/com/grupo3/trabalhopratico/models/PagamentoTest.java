package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Pagamento;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoTest {

    private final Validator validator;

    public PagamentoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidPagamento() {
        Pagamento pagamento = new Pagamento(1L, 100.0, 10.0, "Cartão", LocalDate.now());

        Set<ConstraintViolation<Pagamento>> violations = validator.validate(pagamento);
        assertEquals(0, violations.size());
    }

    @Test
    public void testValorPagoNotNull() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValorDescontado(10.0);
        pagamento.setMetodoPagamento("Cartão");
        pagamento.setDataPagamento(LocalDate.now());

        Set<ConstraintViolation<Pagamento>> violations = validator.validate(pagamento);
        assertEquals(1, violations.size());
        assertEquals("O valor pago é obrigatório", violations.iterator().next().getMessage());
    }
}
