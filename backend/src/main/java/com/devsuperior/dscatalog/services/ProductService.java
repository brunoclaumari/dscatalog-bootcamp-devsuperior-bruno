package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;


@Service
public class ProductService {

	// autowired faz a injeção de dependencia automática
	@Autowired
	private ProductRepository repository;

	// o 'Transactional' define que o método ocorrerá em uma transação.	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		// Usando 'expressão lambda' para transferir Product para ProductDTO
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		/*
		 * No lugar de um try/catch é usado o método 'orElseThrow' que lança
		 * a exceção personalizada criada caso o 'obj' não traga valores 
		 * na requisição. 
		 * */
		Optional<Product> obj = repository.findById(id);
		Product entity=obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			//entity.setName(dto.getName());
			entity = repository.save(entity);		
			return new ProductDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: "+id);
		}
		
	}
	
	//Único sem Transactional, pois tem que capturar uma exceção e o transactional não deixaria
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: "+id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation!");
		}
		
	}

}

//---- Usando 'for' para transferir Product para ProductDTO	
//List<ProductDTO> listDto=new ArrayList<>();
//
//for (Product cat : list) {
//	listDto.add(new ProductDTO(cat));
//}
//
//return listDto;
