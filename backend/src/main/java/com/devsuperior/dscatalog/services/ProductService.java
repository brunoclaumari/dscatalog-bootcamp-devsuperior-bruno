package com.devsuperior.dscatalog.services;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.dto.UriDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	// autowired faz a injeção de dependencia automática
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private S3Service s3Service;

	@Autowired
	private CategoryRepository categoryRepository;

	// o 'Transactional' define que o método ocorrerá em uma transação.
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Long categoryId, String name, PageRequest pageRequest) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));

		Page<Product> list = repository.find(categories, name, pageRequest);
		// Usando 'expressão lambda' para transferir Product para ProductDTO
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		/*
		 * No lugar de um try/catch é usado o método 'orElseThrow' que lança a exceção
		 * personalizada criada caso o 'obj' não traga valores na requisição.
		 */
		Optional<Product> obj = repository.findById(id);
		// Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity
		// not found"));
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}

	}

	// Único sem Transactional, pois tem que capturar uma exceção e o transactional
	// não deixaria
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation!");
		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {

		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());

		entity.getCategories().clear();

		dto.getCategories().forEach(catDto -> {
			// Category cat=categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(categoryRepository.getOne(catDto.getId()));
		});
//		
//		Exemplo do professor com for para substituir linha 103 a 105
//		for (CategoryDTO catDto : dto.getCategories()) {
//			Category cat = categoryRepository.getOne(catDto.getId());
//			entity.getCategories().add(cat);
//		}

	}

	public UriDTO uploadFile(MultipartFile file) {
		 URL url = s3Service.uploadFile(file);	
		
		return new UriDTO(url.toString());
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
