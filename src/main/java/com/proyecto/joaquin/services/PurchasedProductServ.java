package com.proyecto.joaquin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.joaquin.models.Product;
import com.proyecto.joaquin.models.Purchased_product;
import com.proyecto.joaquin.models.User;
import com.proyecto.joaquin.repositories.PurchasedProducRepo;

import jakarta.transaction.Transactional;

@Service
public class PurchasedProductServ {

	@Autowired
	private ProductServ prodServ;
	
	@Autowired
	private PurchasedProducRepo purProductRepo;
	
	public Purchased_product savePurchased(Purchased_product purchased) {
		return purProductRepo.save(purchased);
	}
	
	public void deletePurchased(Purchased_product purchased) {
		purProductRepo.delete(purchased);
	}
	
	public Purchased_product getPurchased(Long id) {
		return purProductRepo.findById(id).orElse(null);
	}
	
	public Purchased_product getPurchasedByUserProduct(User user, Product product) {
		return purProductRepo.findByUserBuyerAndProductPurchased(user, product);
	}
	
	@Transactional
	public List<Purchased_product>findByUser(User user){
		return purProductRepo.findByUserBuyer(user);
	}
	
	//Metodo para guardar el producto comprado a la base de datos
	public void agregarProductoCarrito(User user, Product product) {
		Purchased_product purchased_product = getPurchasedByUserProduct(user, product);
		
		product.setStock(product.getStock() - 1);
		
		if (purchased_product == null) {
			Purchased_product purProduct = new Purchased_product();
			purProduct.setUserBuyer(user);
			purProduct.setProductPurchased(product);
			purProduct.setCant(1);
			
			savePurchased(purProduct);
			return;
		} else {
			purchased_product.setCant(purchased_product.getCant() + 1);
			savePurchased(purchased_product);
		}
	}
	
	//Metodo para guardar el producto comprado a la base de datos
	public void restarProductCarrito(User user, Product product) {
		Purchased_product purchased_product = getPurchasedByUserProduct(user, product);
		
		product.setStock(product.getStock() + 1);
		
		purchased_product.setCant(purchased_product.getCant() - 1);
		savePurchased(purchased_product);
		
		if (purchased_product.getCant() <= 0) {
			quitarProductCarrito(user, product);
		}
	}
	
	public void quitarProductCarrito(User user, Product product) {
		Purchased_product purchased_product = getPurchasedByUserProduct(user, product);
		
		product.setStock(product.getStock() + purchased_product.getCant());
		
		deletePurchased(purchased_product);
	}
}
