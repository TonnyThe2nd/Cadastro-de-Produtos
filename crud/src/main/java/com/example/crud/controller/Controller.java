package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;

import com.example.crud.entities.Entities;
import com.example.crud.productDTO.ProductDTO;
import com.example.crud.repository.Repository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastrar")
public class Controller {
	@Autowired
	private Repository repository;
	
	@PostMapping("/produto")
	public ResponseEntity<Entities> newProduct(@RequestBody @Valid ProductDTO produto){
		var novoProduto = new Entities();
		BeanUtils.copyProperties(produto, novoProduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoProduto));
	}
	
	@GetMapping("/produto/{id_produto}")
	public ResponseEntity<Object> getProduct(@PathVariable(value="id_produto")UUID idProduto){
		Optional<Entities> produto = repository.findById(idProduto);
		if(produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found!!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produto.get());
	}
	
	@GetMapping("/produto/cadastros")
	public ResponseEntity<List<Entities>> getAllProduct(){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}
	
	@DeleteMapping("/produto/delete/{id_produto}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id_produto") UUID idProduto){
		Optional<Entities> produto = repository.findById(idProduto);
		if(produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found or has already been excluded!!");
		}
		repository.delete(produto.get());
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!!");
	}
	@PutMapping("/produto/update/{id_produto}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id_produto") UUID id, @RequestBody @Valid ProductDTO produtoDTO){
		Optional<Entities> produto = repository.findById(id);
		if(produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found!!");
		}
		var novoProduto = produto.get();
		BeanUtils.copyProperties(produtoDTO, novoProduto);
		repository.save(novoProduto);
		return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully!!");
	}
}
