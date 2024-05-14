package com.grupo3.trabalhopratico.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> getProdutos(){
        return produtoService.getProdutos();
    }

    @PostMapping
    public void registerNewProduto(@RequestBody Produto produto){
        produtoService.addNewProduto(produto);
    }

    @DeleteMapping(path = "{produtoId}")
    public void deleteProduto(@PathVariable("produtoId") Long produtoId){
        produtoService.deleteProduto(produtoId);
    }

    @PutMapping(path = "{produtoId}")
    public void updateProduto(
            @PathVariable("produtoId") Long produtoId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) double preco){
        produtoService.updateProduto(produtoId, nome, preco);
    }
}
