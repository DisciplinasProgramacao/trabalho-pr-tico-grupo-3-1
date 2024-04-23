package com.grupo3.trabalhopratico.produto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProdutoConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProdutoRepository repository){
        return args -> {
            Produto limonada = new Produto(
                    "limonada",
                    7.99
            );

            Produto hamburguer = new Produto(
                    "hamburguer",
                    15.99
            );

            repository.saveAll(
                    List.of(limonada, hamburguer)
            );
        };
    }
}
