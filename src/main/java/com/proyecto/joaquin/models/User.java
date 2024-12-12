package com.proyecto.joaquin.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	//VARIABLE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2, message = "se necesitan al menos dos caracteres")
	private String firsName;

	@Size(min=2, message = "se necesitan al menos dos caracteres")
	private String lastName;
	
	private String type;

	@Email(message = "email invalido")
	private String email;
	
	@Size(min=6, message = "la contraseña necesita al menos 6 caracteres")
	private String password;
	
	@Transient
	@Size(min=6, message = "la confimacion necesita al menos 6 caracteres")
	private String comfirm;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> ProductoForSale;
	
	@OneToMany(mappedBy = "userBuyer", fetch = FetchType.LAZY)
	private List<Purchased_product> purchasedProducts
;
	
	
	
	//CONSTRUCT
	public User() {
	}

	
	//GETTERS AND SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}
	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProductoForSale() {
		return ProductoForSale;
	}
	public void setProductoForSale(List<Product> productoForSale) {
		ProductoForSale = productoForSale;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getComfirm() {
		return comfirm;
	}
	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}

	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	public List<Purchased_product> getPurchasedProducts() {
		return purchasedProducts;
	}


	public void setPurchasedProducts(List<Purchased_product> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}


	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updateAt = new Date();
	}
}
