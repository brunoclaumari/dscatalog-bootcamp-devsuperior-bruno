package com.devsuperior.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.services.CategoryService;

//Esta classe implementa os elementos REST
//o annotation abaixo é uma forma simples de configuração no código

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	//autowired faz a injeção de dependencia automática
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();		
		return ResponseEntity.ok().body(list);		
	}
	

}
