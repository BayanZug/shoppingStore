package com.retailer.shop.entity;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 *Class that connects the product table
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
    private Long id;
	@NotEmpty
    private String name;
	@NotEmpty
    private String description;
	@Column(columnDefinition = "varchar(50) default 'assets/images/no-image-available.jpg'")
    private String image = "assets/images/no-image-available.jpg";
	@DecimalMin(value = "0.001")
    private Double price;
	@Lob
	byte[] photo;
	@DecimalMin(value = "0")
	@DecimalMax(value = "100")
    private Double discount;
	@Min(0)
	private int quantity;
	
	
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public Double getDiscountPrice() {
		if(discount==null)
			discount=0.0;
		return price-(price*discount/100);
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Product product = (Product) o;

	        return id.equals(product.id);
	    }

	    @Override
	    public int hashCode() {
	        return id.hashCode();
	    }
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
}
