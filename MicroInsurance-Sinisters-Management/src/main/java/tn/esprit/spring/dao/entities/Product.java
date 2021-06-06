package tn.esprit.spring.dao.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product implements Serializable {
	
	public Product(int id, Category category, int productScoring) {
		super();
		this.id = id;
		this.category = category;
		this.productScoring = productScoring;
	}


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Product_ID")
	private int id; // Clé primaire
	
	
	
	@Column(name="category")
	@Enumerated(EnumType.STRING)
	private Category category ;
	
	
	@Column(name="productScoring")
	private int productScoring;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getProductScoring() {
		return productScoring;
	}


	public void setProductScoring(int productScoring) {
		this.productScoring = productScoring;
	}

}

