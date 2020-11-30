package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	//autowired faz a injeção de dependencia automática
	@Autowired
	private CategoryRepository repository;
	
	//o 'Transactional' define que o método ocorrerá
	//em uma transação
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		return repository.findAll();
	}
	
}
