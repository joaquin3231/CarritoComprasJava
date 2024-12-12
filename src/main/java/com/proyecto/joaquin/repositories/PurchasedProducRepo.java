package com.proyecto.joaquin.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.joaquin.models.Product;
import com.proyecto.joaquin.models.Purchased_product;
import com.proyecto.joaquin.models.User;
import java.util.List;



@Repository
public interface PurchasedProducRepo extends CrudRepository<Purchased_product, Long> {
	Purchased_product findByUserBuyerAndProductPurchased(User user, Product product);
	
	List<Purchased_product> findByUserBuyer(User userBuyer);
}
