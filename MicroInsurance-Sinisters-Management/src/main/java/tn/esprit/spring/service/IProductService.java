package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Product;
import tn.esprit.spring.dao.entities.User;

public interface IProductService {
	
	List<Product> retrieveAllProducts();
	Product addProduct(Product p);
	 void deleteProduct(int id);
	 User updateProduct(Product u);
	 User retrieveProduct(int id);

}
