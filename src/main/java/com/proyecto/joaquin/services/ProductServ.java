package com.proyecto.joaquin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.joaquin.models.Product;
import com.proyecto.joaquin.repositories.ProductRepo;

@Service
public class ProductServ {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}
	
	public Product getProduct(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
}
