package com.produto.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.produto.api.model.Produto;
import com.produto.api.repository.ProdutoRepository;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/Produto")
    public List<Produto> listarProdutos() { return produtoRepository.findAll();}


    @PostMapping("/Produto")
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/Produto/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        return produtoRepository.findById(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
        }
    }


    @DeleteMapping("/Produto/{id}")
    public String excluir(@PathVariable Long id){
        if(!produtoRepository.existById(id)){
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}