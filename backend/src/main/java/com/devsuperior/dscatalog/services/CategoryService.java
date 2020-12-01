package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	// autowired faz a injeção de dependencia automática
	@Autowired
	private CategoryRepository repository;

	// o 'Transactional' define que o método ocorrerá em uma transação.	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		// Usando 'expressão lambda' para transferir Category para CategoryDTO
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		/*
		 * No lugar de um try/catch é usado o método 'orElseThrow' que lança
		 * a exceção personalizada criada caso o 'obj' não traga valores 
		 * na requisição. 
		 * */		
		Category entity=obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return new CategoryDTO(entity);
	}

}

//---- Usando 'for' para transferir Category para CategoryDTO	
//List<CategoryDTO> listDto=new ArrayList<>();
//
//for (Category cat : list) {
//	listDto.add(new CategoryDTO(cat));
//}
//
//return listDto;
